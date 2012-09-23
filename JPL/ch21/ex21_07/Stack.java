package ch21.ex21_07;

import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

class Stack<E> {
  private List<E> list;

  Stack() {
    list = new ArrayList<E>();
  }

  E push(E item) {
    list.add(item);
    return item;
  }

  E pop() {
    E obj = peek();
    list.remove(size() - 1);
    return obj;
  }

  E peek() {
    if (empty())
      throw new EmptyStackException();
    return list.get(size() - 1);
  }

  boolean empty() {
    return size() == 0;
  }

  int search(E item) {
    int index = list.lastIndexOf(item);
    if (index >= 0)
      return size() - index;
    return -1;
  }

  int size() {
    return list.size();
  }
}
