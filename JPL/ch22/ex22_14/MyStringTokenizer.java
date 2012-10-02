package ch22.ex22_14;

import java.util.StringTokenizer;

class MyStringTokenizer {

  public static double sumString(String str) {
    StringTokenizer tokens = new StringTokenizer(str, " ");
    double sum = 0;
    while (tokens.hasMoreTokens()) {
      sum += Double.valueOf(tokens.nextToken());
    }
    return sum;
  }

  public static void main(String[] args) {
    String str = "0.1 0.2 1.3 0.4";
    System.out.println(sumString(str));
  }
}
