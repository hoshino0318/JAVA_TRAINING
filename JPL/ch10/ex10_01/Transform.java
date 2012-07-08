package ch10.ex10_01;

class Transform {
  public static String str2JavaString(String source) {
    String transformedStr = "";

    for (int i = 0; i < source.length(); ++i) {
      char ch = source.charAt(i);
      if (ch == '\n') {
        transformedStr += "\\n";
      } else if (ch == '\t') {
        transformedStr += "\\t";
      } else if (ch == '\b') {
        transformedStr += "\\b";
      } else if (ch == '\r') {
        transformedStr += "\\r";
      } else if (ch == '\f') {
        transformedStr += "\\f";
      } else if (ch == '\\') {
        transformedStr += "\\\\";
      } else if (ch == '\'') {
        transformedStr += "\\\'";
      } else if (ch == '\"') {
        transformedStr += "\\\"";
      } else {
        transformedStr += ch;
      }
    }

    return transformedStr;
  }

  public static void main(String[] args) {
    System.out.println(str2JavaString("hoge\""));
  }
}
