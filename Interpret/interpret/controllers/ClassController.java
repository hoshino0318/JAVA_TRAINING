package interpret.controllers;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import interpret.views.*;
import interpret.models.*;

public class ClassController {
  private MainFrame mainFrame;
  private ObjectDialog objectDialog;
  private ArrayDialog arrayDialog;
  private ConstructorModel constModel;
  private ObjectModel objectModel;
  private MethodModel methodModel;
  private FieldModel fieldModel;

  private final char[] forbiddenChars = {'[', ']'};

  public ClassController(MainFrame mainFrame, ObjectDialog objectDialog, ArrayDialog arrayDialog) {
    this.mainFrame = mainFrame;
    this.objectDialog = objectDialog;
    this.arrayDialog = arrayDialog;
    objectDialog.setClassController(this);
    arrayDialog.setClassController(this);
    constModel = new ConstructorModel();
    objectModel = new ObjectModel();
    methodModel = new MethodModel();
    fieldModel = new FieldModel();
  }

  public void searchButton(String text) {
    if (isEmptyString(text)) return;

    Class<?> cls = null;
    try {
      cls = Class.forName(text);
    } catch (ClassNotFoundException e) {
      printException(e);
      System.out.println("クラス \"" + text + "\" が見つかりません");
      return;
    }

    mainFrame.setClassLabel(text);

    Constructor<?>[] cons = cls.getConstructors();
    for (Constructor<?> con : cons) {
      String constName = con.toString();
      constName = simplifyName(constName);
      constModel.saveConstructor(constName, con);
      mainFrame.printConstructor(constName);
    }
  }

  public void selectButton(String constName) {
    Constructor<?> con = constModel.getConstructor(constName);
    if (con == null) return;

    mainFrame.printConstLabel(simplifyName(con.toString()));
  }

  public boolean createAryButton() {
    String clsName = mainFrame.getClassLabel();
    String objName = mainFrame.getAryObjName();
    String aryNumStr = mainFrame.getAryNumStr();

    if (isEmptyString(clsName)) {
      System.out.println("クラス名を選択してください");
      return false;
    } else if (isEmptyString(objName)) {
      System.out.println("オブジェクト名を入力してください");
      return false;
    } else if (isForbiddenString(objName)) {
      for (int i = 0; i < forbiddenChars.length; ++i)
        System.out.print("'" + forbiddenChars[i] + "', ");
      System.out.println(" は禁則文字です");
      return false;
    } else if (objectModel.containsObject(objName)) {
      System.out.println("同名のオブジェクトが既に存在します");
      return false;
    }

    int aryNum = 0;
    try {
      aryNum = Integer.parseInt(aryNumStr);
    } catch (NumberFormatException e) {
      printException(e);
      System.out.println("整数値を入力してください: " + aryNumStr);
      return false;
    }

    if (aryNum <= 0 || aryNum >= 1000) {
      System.out.println("1 以上 1000 未満の整数値を入力してください");
      return false;
    }

    Class<?> cls = null;
    try {
      cls = Class.forName(clsName);
    } catch (ClassNotFoundException e) {
      System.out.println("クラス " + clsName + " が見つかりません");
      return false;
    }

    Object array = Array.newInstance(cls, aryNum);
    objectModel.saveObject(objName, array);

    return true;
  }

  public boolean createButton() {
    String objName = mainFrame.getObjectName();
    String constName = mainFrame.getConstName();
    String params = mainFrame.getParamName();
    if (isEmptyString(objName)) {
      System.out.println("オブジェクト名を入力してください");
      return false;
    } else if (isForbiddenString(objName)) {
      for (int i = 0; i < forbiddenChars.length; ++i)
        System.out.print("'" + forbiddenChars[i] + "', ");
      System.out.println(" は禁則文字です");
      return false;
    } else if (objectModel.containsObject(objName)) {
      System.out.println("同名のオブジェクトが既に存在します");
      return false;
    }

    Constructor<?> con = constModel.getConstructor(constName);
    if (!createObject(objName, con, params)) {
      return false; // オブジェクトの生成失敗
    }

    return true;
  }

  public boolean objectClearButton() {
    if (objectDialog.isVisible() || arrayDialog.isVisible()) {
      System.out.println("ダイアログを閉じてください");
      return false;
    }
    objectModel.clearObjects();
    return true;
  }

  public void constClearButton() {
    constModel.clearConstructors();
  }

  public void methodClear() {
    methodModel.clearMethods();
  }

  public void fieldClear() {
    fieldModel.clearFields();
  }

  private boolean createObject(String objName, Constructor<?> con, String paramStr) {
   String[] params = csvParse(paramStr);
   Object[] pObjs = createParams(con.getGenericParameterTypes(), params);

   if (pObjs == null) {
     System.out.println("パラメータが不正です");
     return false;
   }

   try {
     Object obj = con.newInstance(pObjs);
     objectModel.saveObject(objName, obj);
   } catch (IllegalAccessException e) {
     printException(e);
     return false;
   } catch (InstantiationException e) {
     printException(e);
     return false;
   } catch (InvocationTargetException e) {
     printException(e);
     return false;
   }

   return true;
  }

  public void objectButton() {
    methodModel.clearMethods();
    fieldModel.clearFields();
    objectDialog.allClear();

    String objName = mainFrame.getSelectedObject();
    Object obj = objectModel.getObject(objName);

    if (obj == null) {
      System.out.println("オブジェクトが見つかりません");
      return;
    }

    Class<?> cls = obj.getClass();

    List<Method> methodsA = Arrays.asList(cls.getMethods());
    List<Method> methodsB = Arrays.asList(cls.getDeclaredMethods());
    List<Method> methods = new ArrayList<Method>();
    methods.addAll(methodsA);
    methods.addAll(methodsB);
    methods = toUniqueList(methods);
    Method[] methodAry = methods.toArray(new Method[]{});
    ObjectPair[] methodPairs = new ObjectPair[methodAry.length];
    for (int i = 0; i < methodAry.length; ++i) {
      Method method = methodAry[i];
      String simpleMethodName = simplifyName(method.toString());
      methodPairs[i] = new ObjectPair(simpleMethodName, method);
    }

    List<Field> fieldsA = Arrays.asList(cls.getFields());
    List<Field> fieldsB = Arrays.asList(cls.getDeclaredFields());
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(fieldsA);
    fields.addAll(fieldsB);
    fields = toUniqueList(fields);
    Field[] fieldAry = fields.toArray(new Field[]{});
    ObjectPair[] fieldPairs = new ObjectPair[fieldAry.length];
    for (int i = 0; i < fieldAry.length; ++i) {
      Field field = fieldAry[i];
      String fieldName = field.toString();
      fieldName = simplifyName(fieldName);
      fieldPairs[i] = new ObjectPair(fieldName, field);
    }

    Arrays.sort(methodPairs);
    Arrays.sort(fieldPairs);

    for (ObjectPair mPair : methodPairs) {
      objectDialog.printMethod(mPair.key);
      methodModel.saveMethod(mPair.key, (Method)mPair.obj);
    }

    for (ObjectPair fPair : fieldPairs) {
      objectDialog.printField(fPair.key);
      fieldModel.saveField(fPair.key, (Field)fPair.obj);
    }

    objectDialog.setTitleLabel(objName);
    objectDialog.setVisible(true);
  }

  public void arrayButton() {
    String objName = mainFrame.getSelectedObject();
    Object obj = objectModel.getObject(objName);

    if (obj == null) {
      System.out.println("オブジェクトが見つかりません");
      return;
    } else if (!obj.getClass().isArray()) {
      System.out.println("配列オブジェクトを選択してください");
      return;
    }

    int length = Array.getLength(obj);
    Class<?> cls = obj.getClass();

    arrayDialog.setObjName(objName);
    arrayDialog.setAryNum("[" + String.valueOf(length) + "]");
    arrayDialog.setClassNameLabel(cls.getCanonicalName());

    arrayDialog.setObjectTable(objName, (Object[])obj);
    arrayDialog.setVisible(true);
  }

  public boolean setArrayButton() {
    String objName = arrayDialog.getObjName();
    String clsName = arrayDialog.getClassName();
    clsName = clsName.substring(0, clsName.length() - 2);
    String param = arrayDialog.getParam();
    int arrayIndex = arrayDialog.getSelectedRowIndex();
    Object obj = objectModel.getObject(objName);

    if (isEmptyString(param)) {
      System.out.println("パラメータを入力してください");
      return false;
    } else if (obj == null) {
      System.out.println("オブジェクトが見つかりません " + objName);
      return false;
    }

    Class<?> cls = null;
    try {
      cls = Class.forName(clsName);
    } catch (ClassNotFoundException e) {
      System.out.println("クラスが見つかりません " + clsName);
      printException(e);
      return false;
    }

    Object[] pObjs = createParams(new Type[]{cls}, new String[]{param});
    if (pObjs == null) {
      System.out.println("パラメータが不正です");
      return false;
    }

    Array.set(obj, arrayIndex, pObjs[0]);
    String objAryName = objName + "[" + arrayIndex + "]";
    System.out.println(objAryName + " を " + pObjs[0] + " に設定しました");

    objectModel.saveObject(objAryName, pObjs[0]);
    mainFrame.addObjectName(objAryName);
    arrayDialog.setObjectTable(objName, (Object[])obj);

    return true;
  }

  public void fieldSelectButton() {
    String objName = objectDialog.getTitleLabel();
    Object obj = objectModel.getObject(objName);
    String fieldName = objectDialog.getFieldName();
    Field field = fieldModel.getField(fieldName);

    if (field == null) {
      System.out.println("フィールドが見つかりません");
      return;
    }

    field.setAccessible(true);
    try {
      Object tmp = field.get(obj);
      if (tmp == null)
        objectDialog.setFieldArea("null");
      else
        objectDialog.setFieldArea(tmp.toString());
    } catch (IllegalAccessException e) {
      printException(e);
      return;
    }
  }

  public boolean methodCallButton() {
    String objName = objectDialog.getTitleLabel();
    String methodName = objectDialog.getMethodName();
    Object obj = objectModel.getObject(objName);
    Method method = methodModel.getMethod(methodName);
    String params = objectDialog.getParams();

    if (method == null) {
      System.out.println("メソッド: " + methodName + " は存在しません");
      return false;
    }

    method.setAccessible(true);
    if (!methodInvoke(obj, method, params)) {
      System.out.println("メソッド呼び出しに失敗しました");
      return false;
    }

    return true;
  }

  public boolean fieldSetButton() {
    String objName = objectDialog.getTitleLabel();
    Object obj = objectModel.getObject(objName);
    String fieldName = objectDialog.getFieldName();
    Field field = fieldModel.getField(fieldName);

    if (field == null) {
      System.out.println("フィールドが見つかりません");
      return false;
    }

    String paramStr = objectDialog.getFieArea();
    String[] params = csvParse(paramStr);

    Object[] pObjs = createParams(new Type[]{field.getGenericType()}, params);

    if (pObjs == null) {
      System.out.println("パラメータが不正です");
      return false;
    }

    field.setAccessible(true);
    //System.out.println("フィールドを設定します");
    try {
      field.set(obj, pObjs[0]);
      System.out.println("フィールド " + pObjs[0] + " に設定しました");
    } catch (IllegalAccessException e) {
      printException(e);
      return false;
    }

    return true;
  }

  private boolean methodInvoke(Object onThis, Method method, String paramStr) {
    String[] params = csvParse(paramStr);
    Object[] pObjs = createParams(method.getGenericParameterTypes(), params);

    if (pObjs == null) {
      System.out.println("パラメータが不正です");
      return false;
    }

    try {
      Object output = method.invoke(onThis, pObjs);
      if (output != null)
        System.out.println(output.toString()); // 可能であれば文字列に変換して出力する
    } catch (IllegalAccessException e) {
      printException(e);
      return false;
    } catch (InvocationTargetException e) {
      printException(e);
      return false;
    }

    return true;
  }

  private Object[] createParams(Type[] types, String[] params) {
    if (types.length == 0)
      return new Object[0];
    else if (types.length != params.length)
      return null;

    Object[] objects = new Object[types.length];

    for (int i = 0; i < objects.length; ++i) {
      Type type = types[i];
      String param = params[i];

      Class<?> cls = (Class<?>)type;
      String typeName = cls.getSimpleName();
      Object obj = objectModel.getObject(param);

      if (objectModel.containsObject(param) // オブジェクトが登録されていて
          && cls.isInstance(obj)) {         // 代入互換の場合
        objects[i] = obj;
      } else if (getObject(param) != null) {
        objects[i] = getObject(param);
      } else if (typeName.equals("byte") || typeName.equals("Byte")) {
        objects[i] = Byte.valueOf(param);
      } else if (typeName.equals("short") || typeName.equals("Short")) {
        objects[i] = Short.valueOf(param);
      } else if (typeName.equals("int") || typeName.equals("Integer")) {
        objects[i] = Integer.valueOf(param);
      } else if (typeName.equals("long") || typeName.equals("Long")) {
        objects[i] = Long.valueOf(param);
      } else if (typeName.equals("char") || typeName.equals("Character")) {
        if (param.length() != 1) {
          return null;
        } else {
          objects[i] = Character.valueOf(param.charAt(0));
        }
      } else if (typeName.equals("float") || typeName.equals("Float")) {
        objects[i] = Float.valueOf(param);
      } else if (typeName.equals("double") || typeName.equals("Double")) {
        objects[i] = Double.valueOf(param);
      } else if (typeName.equals("boolean") || typeName.equals("Boolean")) {
        objects[i] = Boolean.valueOf(param);
      } else if (typeName.equals("String")) {
        objects[i] = param;
      } else {
        return null;
      }
    }

    return objects;
  }

  private String[] csvParse(String csv) {
    if (isEmptyString(csv))
      return new String[0];

    String[] ret = csv.split(",");

    for (int i = 0; i < ret.length; ++i) {
      ret[i] = ret[i].trim(); // 前後の空白は削除する
    }

    return ret;
  }

  /* static フィールドのオブジェクトであれば返す */
  /* ex. Color.red */
  private Object getObject(String str) {
    int endIndex = str.lastIndexOf('.');
    if (endIndex == -1)  return null;

    String className = str.substring(0, endIndex);
    Class<?> cls = null;
    try {
      cls = Class.forName(className);
    } catch (ClassNotFoundException e) {
      printException(e);
      return null;
    }

    String fieldName = str.substring(endIndex + 1);
    Field field = null;
    try {
      field = cls.getField(fieldName);
    } catch (NoSuchFieldException e) {
      printException(e);
      return null;
    }

    Object obj = null;
    try {
      obj = field.get(null);
    } catch (IllegalAccessException e) {
      printException(e);
      return null;
    }

    return obj;
  }

  /* 名前を簡約化する */
  private String simplifyName(String name) {
    String str = "";

    String[] tmpAry = name.split(" "); // 修飾子と分ける

    for (int i = 0; i < tmpAry.length; ++i) {
      if (tmpAry[i].indexOf('.') != -1) { // . を含むものを簡約化する
        str = tmpAry[i];

        if (str.indexOf('(') != -1) { // メソッドの時
          int parenIndex = str.indexOf('(');
          int eParenIndex = str.indexOf(')');
          int beginIndex = str.lastIndexOf('.', parenIndex) + 1;
          String mName = str.substring(beginIndex, parenIndex);
          //tmpAry[i] = mName;
          /* パラメータも簡約 */
          String params = str.substring(parenIndex + 1, eParenIndex);
          String[] paramAry = params.split(",");
          for (int j = 0; j < paramAry.length; ++j) {
            paramAry[j] = simplifyName(paramAry[j]);
          }
          StringBuffer buf = new StringBuffer();
          for (String s : paramAry) {
            buf.append(s);
            buf.append(",");
          }
          params = buf.toString();
          params = params.substring(0, params.length() - 1);
          tmpAry[i] = mName + "(" + params + ")";
        } else {
          int beginIndex = str.lastIndexOf('.') + 1;
          tmpAry[i] = str.substring(beginIndex);
        }
      }
    }

    StringBuffer buf = new StringBuffer();
    for (String s : tmpAry) {
      buf.append(s);
      buf.append(" ");
    }
    String ret = buf.toString();
    ret = ret.substring(0, ret.length() - 1);

    return ret;
  }

  private boolean isEmptyString(String str) {
    if (str.length() == 0) return true;
    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) != ' ')
        return false;
    }
    return true;
  }

  private boolean isForbiddenString(String str) {
    for (int i = 0; i < forbiddenChars.length; ++i) {
      if (str.indexOf(forbiddenChars[i]) != -1)
        return true;
    }
    return false;
  }

  private static void printException(Exception e) {
    if (e.getCause() != null) {
      e.getCause().printStackTrace();
    } else {
      e.printStackTrace();
    }
  }

  private static <T> List<T> toUniqueList(List<T> list) {
    Set<T> uniqueSet = new HashSet<T>();
    uniqueSet.addAll(list);
    List<T> uniqueList = new ArrayList<T>();
    uniqueList.addAll(uniqueSet);
    return uniqueList;
  }

  class ObjectPair implements Comparable<ObjectPair> {
    String key;
    Object obj;

    ObjectPair(String key, Object obj) {
      this.key = key;
      this.obj = obj;
    }

    @Override
    public int compareTo(ObjectPair other) {
        return key.compareTo(other.key);
    }
  }
}
