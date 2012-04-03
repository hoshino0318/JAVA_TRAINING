package ch01.ex01_15;

interface SubLookup extends Lookup {
  /** name と 関連付けられた value を追加する*/
  void add(String name, Object value);
  
  /** name と関連付けされた値を削除し，値を返す
   * そのような値がなければ null を返す */
  Object remove(String name);
}
