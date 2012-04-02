package ch01.ex01_10;

class ImprovedFibonacci {
  static final int MAX_INDEX = 9;
  static final int FIBPAIRS_SIZE = 100;
  static Pair[] fibPairs = new Pair[FIBPAIRS_SIZE];

  /**
   * 偶数要素に '*' を付けて，フィボナッチ数列の
   * 最初の方の要素を表示する
   */
  public static void main(String[] args) {
    int lo = 1, hi = 1;
    int p = 0;
    fibPairs[p++] = new Pair(lo, false);
    for (int i = 2; i <= MAX_INDEX; ++i) {
      fibPairs[p++] = new Pair(hi, (hi % 2 == 0)); 
      hi = hi + lo;
      lo = hi - lo;
    }
    
    for (int i = 0; i < p; ++i) {
      String mark;
      if (fibPairs[i].isEven) 
        mark = " *";
      else 
        mark = "";
      System.out.println((i+1) + ": " + fibPairs[i].value + mark);
    }
  }  
}

class Pair {
  int value = 0;
  boolean isEven = false;
  Pair() {}
  Pair(int value, boolean isEven) {
    this.value = value;
    this.isEven = isEven;
  }
}