package ch04.ex04_02;

class SortString implements SortHarness<String> {
  private String[] values;
  
  public SortString(String[] values) {
    this.values = values;
  }

  @Override
  public void sort() {
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        if (compare(i, j)) {
          swap(i, j);
        }
      }
    }
  }

  @Override
  /** values[i] が values[j] より文字列が長い場合は true を返す
   * それ以外は false を返す */
  public boolean compare(int i, int j) {
    if (values[i].length() > values[j].length()) 
      return true;
    return false;
  }

  @Override
  public String probe(int i) {
    return values[i];
  }

  @Override
  public void swap(int i, int j) {
    String tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  }
}
