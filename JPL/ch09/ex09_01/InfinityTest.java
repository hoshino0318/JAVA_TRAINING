package ch09.ex09_01;

class InfinityTest {
  public static void main(String[] args) {
    System.out.println("+: " + (Float.POSITIVE_INFINITY + Float.POSITIVE_INFINITY));
    System.out.println("-: " + (Float.POSITIVE_INFINITY - Float.POSITIVE_INFINITY));
    System.out.println("*: " + (Float.POSITIVE_INFINITY * Float.POSITIVE_INFINITY));
    System.out.println("/: " + (Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY));
    
    System.out.println();
    
    System.out.println("+: " + (Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));
    System.out.println("-: " + (Float.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY));
    System.out.println("*: " + (Float.POSITIVE_INFINITY * Float.NEGATIVE_INFINITY));
    System.out.println("/: " + (Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY));

  }
}
