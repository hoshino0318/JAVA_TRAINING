package ch03.ex03_11;

abstract class ImprovedSortDouble {
  private double [] values;
  private final SortMetrics curMetrics = new SortMetrics();
  
  /** 全ソートをするために呼び出される */
  public final SortMetrics sort(double[] data) {
    values = data;
    curMetrics.init();
    doSort();
    return getMetrics();
  }
  
  public final SortMetrics getMetrics() {
    return curMetrics.clone();
  }
  
  /** 拡張したクラスが要素の数を知るため */
  protected final int getDataLength() {
    return values.length;
  }
  
  /** probe メソッドを削除 
   * SortMetrics の probeCnt が不必要になることや
   * 他のソートアルゴリズムへの影響があるかもしれない */
  
  /** 拡張したクラスが要素を比較するため */
  protected final int compare(int i, int j) {
    curMetrics.compareCnt++;
    double d1 = values[i];
    double d2 = values[j];
    if (d1 == d2) 
      return 0;
    else
      return (d1 < d2 ? -1 : 1);
  }
  
  /** 拡張したクラスが要素を交換するため */
  protected final void swap(int i, int j) {
    curMetrics.swapCnt++;
    double tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  }
  
  /** 拡張したクラスが実装する -- ソートするのに使用される */
  protected abstract void doSort();

}
