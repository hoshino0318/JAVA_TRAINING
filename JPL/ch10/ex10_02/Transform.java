package ch10.ex10_02;

class Transform {
  public static String str2JavaString(String source) {
    String transformedStr = "";

    for (int i = 0; i < source.length(); ++i) {
      char ch = source.charAt(i);

      switch (ch) {
      case '\n':
        transformedStr += "\\n";
        break;
      case '\t':
        transformedStr += "\\t";
        break;
      case '\b':
        transformedStr += "\\b";
        break;
      case '\r':
        transformedStr += "\\r";
        break;
      case '\f':
        transformedStr += "\\f";
        break;
      case '\\':
        transformedStr += "\\\\";
        break;
      case '\'':
        transformedStr += "\\\'";
        break;
      case '\"':
        transformedStr += "\\\"";
        break;
      default:
        transformedStr += ch;
        break;
      }
    }

    return transformedStr;
  }

  public static void main(String[] args) {
    System.out.println(str2JavaString("hoge\""));
  }
}
