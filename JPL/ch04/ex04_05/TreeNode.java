package ch04.ex04_05;

abstract class TreeNode<T> {
  T value;
  TreeNode<T> parent;
  TreeNode<T>[] children;

  abstract boolean search(T target);
  abstract void add(T value);
  abstract boolean remove(T target);
}
