package ch01.ex01_15;

interface SubLookup extends Lookup {
  /** name と 関連付けられた object を追加する*/
  void add(Object object, String name);
  
  /** name と関連付けされた値を削除する
   * そのような値がなければ null を返す */
  Object remove(String name);
}
