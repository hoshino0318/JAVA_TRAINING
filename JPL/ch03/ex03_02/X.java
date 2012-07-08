package ch03.ex03_02;

class X {
  protected int xMask = 0x00ff;
  protected int fullMask;

  {
    System.out.printf("XXXXX%n");
  }

  public X() {
    System.out.printf("start  X(): xMask = %04x, fullMask = %04x%n", xMask, fullMask);
    fullMask = xMask;
    System.out.printf("finish X(): xMask = %04x, fullMask = %04x%n", xMask, fullMask);
  }

  public int mask(int orig) {
    return (orig & fullMask);
  }
}
