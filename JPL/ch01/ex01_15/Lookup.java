package ch01.ex01_15;

interface Lookup {
  /** name と関連付けされた値を返す。
   * そのような値がなければ null を返す */
  Object find(String name);
}
