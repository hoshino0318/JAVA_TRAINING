package ch13.ex13_06;

class MyString {
  public static String separateStr(String str) {
    return MyString.separateStr(str, ',', 3);
  }

  public static String separateStr(String str, char delimiter, int digit) {
    if (digit <= 0)  digit = 1;

    StringBuilder retBuilder = new StringBuilder();

    int count = 0;
    for (int i = str.length() - 1; i >= 0; --i) {
      retBuilder.append(str.charAt(i));
      count++;
      if (count == digit && i != 0) {
        retBuilder.append(delimiter);
        count = 0;
      }
    }

    return retBuilder.reverse().toString();
  }
}
