package ch01.ex01_06;

class Fibonacci {
  static final int MAX = 50;
  static final String title = "Fibonacci ";
  
  /** 値が MAX 未満のフィボナッチ数列を表示する */
  public static void main(String[] args) {
    int lo = 1;
    int hi = 1;
    System.out.println(title + lo);
    while (hi < MAX) {
      System.out.println(title + hi);
      hi = lo + hi;  // 新しい hi
      lo = hi - lo;  /* 新しい lo は，(合計 - 古い lo)
                        すなわち，古い hi */
    }
  }
}
