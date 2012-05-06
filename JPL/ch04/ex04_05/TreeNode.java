package ch04.ex04_05;

abstract class TreeNode<T> {
  T value;
  TreeNode parent;
  TreeNode[] children;

  abstract boolean search(T target);
  abstract void add(T value);
  abstract boolean remove(T target);
}
