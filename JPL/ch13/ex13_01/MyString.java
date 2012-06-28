package ch13.ex13_01;

class MyString {
  private String str;
  
  MyString(String str) {
    this.str = str;
  }
  
  int count(char ch) {
    int c = 0;
    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) == ch)
        c++;
    }
    return c;
  }
  
  @Override
  public String toString() {
    return str;
  }
}
