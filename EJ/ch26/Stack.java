package ch26;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
  private E[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  // elements 配列は push(E) からの E インスタンスだけを含む。
  // そのことは、型安全性を保証するためには十分ではあるが、配列の
  // 実行時の型 E[] ではない。常に Object[] である！
  @SuppressWarnings("unchecked")
  public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(E e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public E pop() {
    if (size == 0)
      throw new EmptyStackException();
    E result = elements[--size];
    elements[size] = null; // 廃れた参照を取り除く
    return result;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void ensureCapacity() {
    if (elements.length == size)
      elements = Arrays.copyOf(elements, 2 * size + 1);
  }

  public static void main(String[] args) {
    Stack<String> stack = new Stack<String>();
    for (String arg : args)
      stack.push(arg);
    while (!stack.isEmpty())
      System.out.println(stack.pop().toUpperCase());
  }
}
