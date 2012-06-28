package ch13.ex13_02;

class MyString {
  private String myStr;

  MyString(String str) {
    this.myStr = str;
  }

  int countStr(String str) {
    int c = 0;

    for (int s = 0, e = str.length(); e <= myStr.length(); ++s, ++e) {
      if (myStr.subSequence(s, e).equals(str))
        c++;
    }

    return c;
  }

  @Override
  public String toString() {
    return myStr;
  }
}
