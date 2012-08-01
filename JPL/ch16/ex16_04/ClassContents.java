package ch16.ex16_04;

import java.lang.annotation.*;

class ClassContents {
  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName(args[0]);
      System.out.println(c);
      printAnnotations(c.getAnnotations());
    } catch (ClassNotFoundException e) {
      System.out.println("unknown class: " + args[0]);
    }
  }

  private static void printAnnotations(Annotation[] annos) {
    for (Annotation a : annos) {
      String decl = a.toString();
      System.out.print(" ");
      System.out.println(decl.replaceAll("java.lang.", ""));
    }
  }
}
