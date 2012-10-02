package ch22.ex22_02;

import java.util.HashSet;
import java.util.Set;

class WhichChars {
  private Set<Character> used = new HashSet<Character>();

  WhichChars(String str) {
    for (int i = 0; i < str.length(); ++i)
      used.add(str.charAt(i)); // 文字に対応したビットをセット
  }

  @Override
  public String toString() {
    String desc = "[";
    for (Character ch : used) {
      desc += ch;
    }
    return desc + "]";
  }

  public static void main(String[] args) {
    WhichChars whichChars = new WhichChars("Testing 1 2 3");
    System.out.println(whichChars);
  }
}
