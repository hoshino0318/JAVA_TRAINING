package ch04.ex04_02;

public interface SortHarness<T> {
  void sort();
  boolean compare(int i, int j);
  T probe(int i);
  void swap(int i, int j);
}
