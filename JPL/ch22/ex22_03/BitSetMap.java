package ch22.ex22_03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

class BitSetMap {
  private Map<BitSet, BitSet> bitMap;

  BitSetMap(String str) {
    bitMap = new HashMap<BitSet, BitSet>();

    for (int i = 0; i < str.length(); ++i) {
      int ch = str.charAt(i);
      int highCh = ch & 0xFF00;
      int lowCh = ch & 0x00FF;
      System.out.println("high = " + highCh + ", low = " + lowCh);
      bitMap.put(intToBit(highCh), intToBit(lowCh));
    }
  }

  @Override
  public String toString() {
    String desc = "[\n";

    for (Map.Entry<BitSet, BitSet> e : bitMap.entrySet()) {
      desc += " [";
      BitSet high = e.getKey();
      int length = high.length();
      String tmp = "";
      for (int i = 0; i < length; ++i)
        if (high.get(length - i - 1))
          tmp += "1";
        else
          tmp += "0";
      desc += formatString(tmp, 8, '0');
      desc += ", ";
      BitSet low = e.getValue();
      length = low.length();
      tmp = "";
      for (int i = 0; i < length; ++i)
        if (low.get(length - i - 1))
          tmp += "1";
        else
          tmp += "0";
      desc += formatString(tmp, 4, '0');
      desc += "],\n";
    }
    desc += "]";
    return desc;
  }

  private static final BitSet intToBit(int n) {
    BitSet b = new BitSet();
    int i = 0;
    while (n != 0) {
      if (n % 2 != 0)
        b.set(i);
      n /= 2;
      i++;
    }
    return b;
  }

  private String formatString(String str, int len, char ch) {
    StringBuilder strBuilder = new StringBuilder(str);
    int count = len - strBuilder.length();
    for (int i = 0; i < count; ++i)
      strBuilder.insert(0, ch);
    return strBuilder.toString();
  }

  public static void main(String[] args) {
    BitSetMap bitSetMap = new BitSetMap("abcde");
    System.out.println(bitSetMap);
  }
}
