package ch04.ex04_04;

public interface Stack<E> {
  /** スタックに value を追加する */
  void push(E value);
  /** スタックの一番上の要素を取り出す */
  E pop();
  /** スタックの一番上の要素を返す */
  E top();
}
