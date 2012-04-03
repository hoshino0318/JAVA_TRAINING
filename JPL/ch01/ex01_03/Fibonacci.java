package ch01.ex01_03;

class Fibonacci {
  static final int MAX = 50;
  
  /** 値が MAX 未満のフィボナッチ数列を表示する */
  public static void main(String[] args) {
    int lo = 1;
    int hi = 1;
    int index = 1;
    String title = "Fibonacci(" + index + ") = ";
    System.out.println(title + lo);
    while (hi < MAX) {
      index++;
      title = "Fibonacci(" + index + ") = "; 
      System.out.println(title + hi);
      hi = lo + hi;  // 新しい hi
      lo = hi - lo;  /* 新しい lo は，(合計 - 古い lo)
                        すなわち，古い hi */
    }
  }
}
