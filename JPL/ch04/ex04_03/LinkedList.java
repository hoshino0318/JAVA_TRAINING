package ch04.ex04_03;

public interface LinkedList<T> {
  T getItem();
  LinkedList<T> next();
  void setNext(LinkedList<T> nextNode);
  int getListLength();
}
