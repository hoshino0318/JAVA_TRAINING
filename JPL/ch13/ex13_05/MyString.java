package ch13.ex13_05;

class MyString {
  public static String separateStr(String str) {
    StringBuilder retBuilder = new StringBuilder();

    int count = 0;
    for (int i = str.length() - 1; i >= 0; --i) {
      retBuilder.append(str.charAt(i));
      count++;
      /* 3 文字区切りかつ最上位の桁でない場合 */
      if (count == 3 && i != 0) {
        retBuilder.append(',');
        count = 0;
      }
    }

    return retBuilder.reverse().toString();
  }
}
