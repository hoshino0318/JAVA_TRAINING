// Invoke.java
import java.lang.reflect.*;

class Invoke {
  public static void main(String[] args) {
    String str = "Invocation. Test";

    Throwable failure = null;
    try {
      Method indexM = String.class.getMethod("indexOf", String.class, int.class);
      //System.out.println((Integer) indexM.invoke(str, ".", 8));
      System.out.println(indexM.invoke(str, ".", 8));
    } catch (NoSuchMethodException e) {
      failure = e;
    } catch (InvocationTargetException e) {
      failure = e;
    } catch (IllegalAccessException e) {
      failure = e;
    }
    if (failure != null)
      failure.printStackTrace();
  }
}
