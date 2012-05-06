package ch04.ex04_04;

public interface RandomAccessList<E> extends List<E> {
  /** index 番目の要素を返す */
  E at(int index);
}
