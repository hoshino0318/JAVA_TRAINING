package ch16.ex16_09;

import java.lang.annotation.*;
import java.lang.reflect.*;


class CompClassDeclaration {
  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName(args[0]);
      showCompleteClassDeclaration(c);
    } catch (ClassNotFoundException e) {
      out.println("unknown class: " + args[0]);
    }
  }

  /* デフォルトで標準出力に表示する */
  private static java.io.PrintStream out = System.out;

  /* インデントを指定して表示する */
  private static void print(String str, int level) {
    String indent = "";
    for (int i = 0; i < level; ++i)
      indent += " ";
    out.print(indent + str);
  }
  private static void println(String str, int level) {
    print(str + "\n", level);
  }

  private static void showCompleteClassDeclaration(Class<?> cls) {
    printPackage(cls);
    printClassDeclaration(cls, 0);
  }

  private static void printPackage(Class<?> cls) {
    Package pkg = cls.getPackage();
    printAnnotations(pkg, 0);
    if (pkg != null)
      out.println("package " + pkg.getName() + ";");
  }

  private static void printClassDeclaration(Class<?> cls, int level) {
    printAnnotations(cls, level);
    printModifiers(cls.getModifiers(), level);
    if (Modifier.isInterface(cls.getModifiers()))
      out.print("interface ");
    else
      out.print("class ");

    out.print(cls.getSimpleName());
    printTypeParameters(cls.getTypeParameters());
    out.print(" ");
    printSuperTypes(cls);

    out.println("{");

    /* フィールドの表示 */
    printFields(cls, level + 4);
    out.println();
    /* メソッドの表示 */
    printMethods(cls, level + 4);
    out.println();
    /* 内部クラスの表示 */
    Class<?>[] clss = cls.getDeclaredClasses();
    if (clss.length > 0) {
      for (Class<?> icls : clss) {
        /* ダミークラスは表示しない */
        if (icls.getCanonicalName() != null)
          printClassDeclaration(icls, level + 4);
      }
    }

    println("}", level);
  }

  private static void printSuperTypes(Class<?> cls) {
    Type sType = cls.getGenericSuperclass();
    if (sType != null) {
      out.print("extends ");
      /* Type を Class オブジェクトに変換する */
      Class<?> scls = null;
      if (sType instanceof Class<?>)
        scls = (Class<?>) sType;
      else if (sType instanceof ParameterizedType)
        scls = (Class<?>)((ParameterizedType)sType).getRawType();
      /* スーパークラスが Object の時は表示しない */
      if (scls != Object.class) {
        print(scls.getSimpleName(), 0);
        printTypeParameters(scls.getTypeParameters());
        print(" ", 0);
      }
    }

    Type[] interfaces = cls.getGenericInterfaces();
    if (interfaces.length > 0)
      out.print("implements ");
    for (Type iface :interfaces) {
      Class<?> iClass = null;
      if (iface instanceof Class<?>)
        iClass = (Class<?>) iface;
      else if (iface instanceof ParameterizedType)
        iClass = (Class<?>)((ParameterizedType) iface).getRawType();
      out.print(iClass.getSimpleName());
      printTypeParameters(iClass.getTypeParameters());
      out.print(", ");
    }
    out.print("\b\b");
  }

  private static void printFields(Class<?> cls, int level) {
    Field[] fields = cls.getDeclaredFields();

    if (fields.length == 0)
      return;
    for (Field field : fields) {
      /* 内部クラスの場合，エンクロージングクラスへの参照は表示しない */
      if (field.getName().startsWith("this"))
        continue;

      printAnnotations(field, level + 4);
      if (field.getAnnotations().length > 0)
        print(" ", 0);
      printModifiers(field.getModifiers(), level);
      /* 型の表示 */
      Type fType = field.getGenericType();
      Class<?> fClass = null;
      if (fType instanceof Class<?>)
        fClass = (Class<?>) fType;
      else if (fType instanceof ParameterizedType)
        fClass = (Class<?>)((ParameterizedType) fType).getRawType();
      else if (fType instanceof TypeVariable)
        fClass = (Class<?>)((TypeVariable<?>) fType).getClass();

      if (fType instanceof TypeVariable)
        out.print(((TypeVariable<?>) fType).getName());
      else {
        out.print(fClass.getSimpleName());
        printTypeParameters(fClass.getTypeParameters());
      }

      out.print(" ");
      out.print(field.getName());
      out.println(";");
    }
  }

  private static void printMethods(Class<?> cls, int level) {
    Method[] methods = cls.getDeclaredMethods();

    if (methods.length == 0)
      return;
    for (Method method : methods) {
      printAnnotations(method, level);
      if (method.getAnnotations().length > 0)
        print(" ", 0);
      printModifiers(method.getModifiers(), level);
      /* 返り値の型の表示 */
      Type rType = method.getGenericReturnType();
      Class<?> rClass = null;
      if (rType instanceof Class<?>)
        rClass = (Class<?>) rType;
      else if (rType instanceof ParameterizedType)
        rClass = (Class<?>)((ParameterizedType) rType).getRawType();
      else
        rClass = void.class;
      out.print(rClass.getSimpleName());
      out.print(" ");

      out.print(method.getName());

      /* パラメータの表示 */
      printMethodParams(method);

      /* 例外の表示 */
      Type[] eTypes = method.getGenericExceptionTypes();
      if (eTypes.length > 0) {
        out.print(" throws ");
        for (Type eType : eTypes) {
          Class<?> eClass = (Class<?>) eType;
          out.print(eClass.getSimpleName() + ", ");
        }
        out.print("\b");
      }
      out.println(";");
    }
  }

  private static void printMethodParams(Method method) {
    out.print("(");
    Type[] pTypes = method.getGenericParameterTypes();
    for (int i = 0; i < pTypes.length; ++i) {
      Type pType = pTypes[i];
      Class<?> pClass = null;
      if (pType instanceof Class<?>)
        pClass = (Class<?>) pType;
      else if (pType instanceof ParameterizedType)
        pClass = (Class<?>)((ParameterizedType) pType).getRawType();
      else
        pClass = void.class;
      printAnnotations(pClass, 0);
      out.print(pClass.getSimpleName() + " param" + (i+1));
      if (i != pTypes.length - 1)
        out.print(", ");
    }
    out.print(")");
  }

  private static void printTypeParameters(TypeVariable<?>[] params) {
    if (params.length > 0) {
      out.print('<');
      for (TypeVariable<?> param : params) {
        out.print(param.getName());
        out.print(", ");
      }
      out.print("\b\b>");
    }
  }

  private static void printAnnotations(AnnotatedElement aElm, int level) {
    Annotation[] anos = aElm.getAnnotations();

    if (anos.length == 0)
      return;
    for (Annotation ano : anos) {
      print(ano.toString() + " ", level);
    }
  }

  private static void printModifiers(int mod, int level) {
    print("", level);
    if (Modifier.isPublic(mod))
      print("public ", 0);
    if (Modifier.isProtected(mod))
      print("protected ", 0);
    if (Modifier.isPrivate(mod))
      print("private ", 0);
    if (Modifier.isAbstract(mod))
      print("abstract ", 0);
    if (Modifier.isStatic(mod))
      print("static ", 0);
    if (Modifier.isFinal(mod))
      print("final ", 0);
    if (Modifier.isTransient(mod))
      print("transient ", 0);
    if (Modifier.isVolatile(mod))
      print("volatile ", 0);
    if (Modifier.isSynchronized(mod))
      print("synchronized ", 0);
    if (Modifier.isNative(mod))
      print("native ", 0);
    if (Modifier.isStrict(mod))
      print("strictfp ", 0);
  }

}
