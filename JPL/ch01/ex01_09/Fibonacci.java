package ch01.ex01_09;

class Fibonacci {
  static final int MAX = 50;
  static final int FIBARRAY_SIZE = 100;
  static int[] fibArray = new int[FIBARRAY_SIZE];

  /** 値が MAX 未満のフィボナッチ数列を表示する */
  public static void main(String[] args) {
    int lo = 1, hi = 1;
    int p = 0;
    fibArray[p++] = lo;
    while (hi < MAX) {
      if (p >= FIBARRAY_SIZE) break;
      fibArray[p++] = hi;
      hi = lo + hi;  // 新しい hi
      lo = hi - lo;  /* 新しい lo は，(合計 - 古い lo)
                        すなわち，古い hi */
    }
    
    for (int i = 0; i < p; ++i) {
      System.out.println("Fibonacci(" + (i + 1) + ") = " + fibArray[i]);
    }
  }
}
