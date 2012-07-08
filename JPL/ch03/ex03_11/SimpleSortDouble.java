package ch03.ex03_11;

class SimpleSortDouble extends SortDouble {

  @Override
  protected void doSort() {
    for (int i = 0; i < getDataLength(); ++i) {
      for (int j = i + 1; j < getDataLength(); ++j) {
        /* デフォルトだと compare を使わずに比較が可能 */
        if (probe(i) > probe(j))
          swap(i, j);
      }
    }
  }
}
