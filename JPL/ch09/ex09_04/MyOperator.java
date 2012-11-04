package ch09.ex09_04;

class MyOperator {
  public static void main(String[] args) {
    System.out.println(3 << 2L  - 1);   // OK, int, 6
    System.out.println((3L << 2) - 1);  // OK, long, 11
    System.out.println(10 < 12 == 6 > 17); // OK, boolean, false
    System.out.println(10 << 12 == 6 >> 17); // OK, boolean, false
    System.out.println(13.5e-1 % Float.POSITIVE_INFINITY); // OK, double, 13.5e-1
    System.out.println(Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY); // OK, double, NaN
    System.out.println(Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY); // OK, double, Double.POSITIVE_INFINITY
    System.out.println(0.0 / -0.0 == -0.0 / 0.0); // OK, boolean, false
    System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE); // OK, int, -1
    System.out.println(Long.MAX_VALUE + 5); // OK, long, - (2 ^ 63 - 4)
    System.out.println((short) 5 * (byte) 10); // OK, int, 50

    int i = 3;
    System.out.println(i < 15 ? 1.72e3f : 0); // OK, float, 1.72e3f
    System.out.println(i++ + i++ + --i); // OK, int, 11
  }
}
