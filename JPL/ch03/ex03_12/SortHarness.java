package ch03.ex03_12;

abstract class SortHarness {
  private Object[] values;
  
  public SortHarness(Object[] values) {
    this.values = values;
  }
  
  /** ソートを実行する */
  public void sort() {
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        if (compare(i, j)) {
          swap(i, j);
        }
      }
    }
  }
  
  /** values[i] が values[j] より大きい場合 true を返す
   * それ以外は false を返す */
  public abstract boolean compare(int i, int j);
  
  protected Object probe(int i) {
    return values[i];
  }
  
  private void swap(int i, int j) {
    Object tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  }
}
