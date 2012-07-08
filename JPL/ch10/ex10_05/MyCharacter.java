package ch10.ex10_05;

class MyCharacter {
  public static void showCharRange(char chLower, char chUpper) {
    if (chLower > chUpper)
      throw new IllegalArgumentException("'chLower' must be lower than 'chUpper'");

    for (char s = chLower; s <= chUpper; ++s) {
      System.out.print(s);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    showCharRange('A', 'Z');
    showCharRange('+', '+');
    showCharRange('a', 'A'); // throw Exception
  }
}
