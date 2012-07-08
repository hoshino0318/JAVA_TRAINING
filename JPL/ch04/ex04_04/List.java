package ch04.ex04_04;

public interface List<E> extends Iterable<E> {
  /** リストに value を追加する */
  void add(E value);
  /** リストから value を削除する */
  void remove(E value);
  /** リストの先頭の要素を返す */
  E head();
  /** リストの最後尾の要素を返す */
  E tail();
}
