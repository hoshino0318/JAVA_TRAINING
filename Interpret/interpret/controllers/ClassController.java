package interpret.controllers;

import java.lang.reflect.*;
import java.util.Arrays;

import interpret.views.*;
import interpret.models.*;

public class ClassController {
  private MainFrame mainFrame;
  private MethodDialog methodDialog;
  private ConstructorModel constModel;
  private ObjectModel objectModel;
  private MethodModel methodModel;

  public ClassController(MainFrame mainFrame, MethodDialog methodDialog) {
    this.mainFrame = mainFrame;
    this.methodDialog = methodDialog;
    methodDialog.setClassController(this);
    constModel = new ConstructorModel();
    objectModel = new ObjectModel();
    methodModel = new MethodModel();
  }

  public void searchButton(String text) {
    if (isEmptyString(text)) return;

    Class<?> cls = null;
    try {
      cls = Class.forName(text);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    if (cls != null) {
      Constructor<?>[] cons = cls.getConstructors();
      constModel.saveConstructors(cons);
      mainFrame.printConstructorList(cons);
    } else {
      System.err.println("No such type: \"" + text + "\"");
    }
  }

  public void selectButton(String constName) {
    Constructor<?> con = constModel.getConstructor(constName);
    if (con == null) return;

    mainFrame.printConstLabel(con.toString());
  }

  public boolean createButton() {
    String objName = mainFrame.getObjectName();
    String constName = mainFrame.getConstName();
    String params = mainFrame.getParamName();
    if (isEmptyString(objName)) {
      System.out.println("オブジェクト名を入力してください");
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

  public void objectClearButton() {
    objectModel.clearObjects();
  }

  public void constClearButton() {
    constModel.clearConstructors();
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
     e.printStackTrace();
     return false;
   } catch (InstantiationException e) {
     e.printStackTrace();
     return false;
   } catch (InvocationTargetException e) {
     e.printStackTrace();
     return false;
   }

   return true;
  }

  public void methodButton() {
    methodModel.clearMethods();

    String objName = mainFrame.getSelectedObject();
    Object obj = objectModel.getObject(objName);

    if (obj == null) {
      System.out.println("オブジェクトが見つかりません");
      return;
    }

    Class<?> cls = obj.getClass();
    Method[] methods = cls.getMethods();

    MethodPair[] methodPairs = new MethodPair[methods.length];

    for (int i = 0; i < methods.length; ++i) {
      Method method = methods[i];
      String simpleMethodName = simplifyMethodName(method.toString());
      methodPairs[i] = new MethodPair(simpleMethodName, method);
    }

    Arrays.sort(methodPairs);

    for (MethodPair mPair : methodPairs) {
      methodDialog.printMethod(mPair.methodName);
      methodModel.saveMethods(mPair.methodName, mPair.method);
    }

    methodDialog.setTitleLabel(objName);
    methodDialog.setVisible(true);
  }

  public boolean methodCallButton() {
    String objName = methodDialog.getTitleLabel();
    String methodName = methodDialog.getMethodName();
    Object obj = objectModel.getObject(objName);
    Method method = methodModel.getMethod(methodName);
    String params = methodDialog.getParams();

    if (!methodInvoke(obj, method, params)) {
      System.out.println("メソッド呼び出しに失敗しました");
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
      System.out.println(output.toString()); // 可能であれば文字列に変換して出力する
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return false;
    } catch (InvocationTargetException e) {
      e.printStackTrace();
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
      String typeName = cls.getName();

      if (objectModel.containsObject(param)) { // オブジェクトが存在する場合はそれを利用する
        objects[i] = objectModel.getObject(param);
      } else if (typeName.equals("byte")) {
        objects[i] = Byte.valueOf(param);
      } else if (typeName.equals("short")) {
        objects[i] = Short.valueOf(param);
      } else if (typeName.equals("int")) {
        objects[i] = Integer.valueOf(param);
      } else if (typeName.equals("long")) {
        objects[i] = Long.valueOf(param);
      } else if (typeName.equals("char")) {
        if (param.length() != 1) {
          return null;
        } else {
          objects[i] = Character.valueOf(param.charAt(0));
        }
      } else if (typeName.equals("float")) {
        objects[i] = Float.valueOf(param);
      } else if (typeName.equals("double")) {
        objects[i] = Double.valueOf(param);
      } else if (typeName.equals("boolean")) {
        objects[i] = Boolean.valueOf(param);
      } else if (typeName.equals("java.lang.String")) {
        objects[i] = param;
      } else if (getObject(param) != null) {
        objects[i] = getObject(param);
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
      e.printStackTrace();
      return null;
    }

    String fieldName = str.substring(endIndex + 1);
    Field field = null;
    try {
      field = cls.getField(fieldName);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
      return null;
    }

    Object obj = null;
    try {
      obj = field.get(null);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }

    return obj;
  }

  /* メソッド名を簡約化する */
  private String simplifyMethodName(String methodName) {
    String str = "";

    String[] tmpAry = methodName.split(" "); // 修飾子と分ける

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
            paramAry[j] = simplifyMethodName(paramAry[j]);
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

  class MethodPair implements Comparable<MethodPair> {
    String methodName;
    Method method;

    MethodPair(String methodName, Method method) {
      this.methodName = methodName;
      this.method = method;
    }

    @Override
    public int compareTo(MethodPair other) {
        return methodName.compareTo(other.methodName);
    }
  }
}
