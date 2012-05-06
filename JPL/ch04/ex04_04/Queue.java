package ch04.ex04_04;

public interface Queue<E> extends Iterable<E> {
  /** value をキューに追加する */  
  void enqueue(E value);
  /** キューから要素を取り出す */  
  E dequeue();
  /** キューの先頭を返す */
  E front();
  /** キューの最後尾を返す */
  E back();
}
