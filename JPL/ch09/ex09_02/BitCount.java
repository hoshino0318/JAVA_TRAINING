package ch09.ex09_02;

class BitCount {

  public static int bitCount(int val) {
    int ret = 0;
    int mask = 0x01;

    while (val != 0) {
      ret += val & mask;
      val >>>= 1;
    }

    return ret;
  }
}
