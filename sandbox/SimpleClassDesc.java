
import java.lang.reflect.*;
import static java.lang.System.out;
import static java.lang.System.err;

class SimpleClassDesc {
  public static void main(String[] args) {
    Class type = null;

    if (args.length == 0) {
      usage();
      return;
    }

    try {
      type = Class.forName(args[0]);
    } catch (ClassNotFoundException e) {
      err.println(e);
      return;
    }

    out.print("class " + type.getSimpleName());

    Class superclass = type.getSuperclass();
    if (superclass != null) {
      out.println(" extends " +
          superclass.getCanonicalName());
    } else {
      out.println();
    }

    Method[] methods = type.getDeclaredMethods();
    for (Method m : methods) {
      if (Modifier.isPublic(m.getModifiers()))
        out.println(" " + m);
    }

  }

  private static void usage() {
    String msg = "Usage:\n";
    msg += "  $ java SimpleClassDesc className";
    out.println(msg);
  }
}
