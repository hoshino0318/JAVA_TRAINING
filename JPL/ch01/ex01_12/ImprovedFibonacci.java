package ch01.ex01_12;

class ImprovedFibonacci {
  static final int MAX_INDEX = 9;
  static final int FIBSTRINGS_SIZE = 100;
  static String[] fibStrings = new String[FIBSTRINGS_SIZE];

  /**
   * 偶数要素に '*' を付けて，フィボナッチ数列の
   * 最初の方の要素を表示する
   */
  public static void main(String[] args) {
    int lo = 1, hi = 1;
    int p = 0;
    fibStrings[p++] = Integer.toString(lo);
    for (int i = 2; i <= MAX_INDEX; ++i) {
      if (p >= FIBSTRINGS_SIZE) break;
      fibStrings[p] = Integer.toString(hi);
      if (hi % 2 == 0)
        fibStrings[p] += " *";
      hi = hi + lo;
      lo = hi - lo;
      p++;
    }
    
    for (int i = 0; i < p; ++i) {
      System.out.println("Fibonacci(" + (i+1) + ") = " + fibStrings[i]);
    }
  }  
}
