package ch13.ex13_03;

import java.util.List;
import java.util.ArrayList;

class MyString {
  public static String[] delimitedString(String from, char start, char end) {
    List<String> retList = new ArrayList<String>();

    int i = 0;
    while (true) {
      /* 開始文字の検索 */
      int startPos = from.indexOf(start, i);
      if (startPos == -1) // 開始文字が見つからない
        break;

      /* 終了文字の検索 */
      int endPos = from.indexOf(end, startPos+1);
      if (endPos == -1) // 終了文字が見つからない
        break;

      /* 文字列をリストに格納 */
      retList.add(from.substring(startPos, endPos+1));

      /* インデックスを更新 */
      i = endPos + 1;
    }

    return retList.toArray(new String[0]);
  }
}
