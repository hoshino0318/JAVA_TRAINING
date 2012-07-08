package ch03.ex03_02;

class Y extends X {
  protected int yMask = 0xff00;

  {
    System.out.printf("YYYYY%n");
  }

  public Y() {
    System.out.printf("start  Y(): yMask = %4x, fullMask = %04x%n", yMask, fullMask);
    fullMask |= yMask;
    System.out.printf("finish Y(): yMask = %4x, fullMask = %04x%n", yMask, fullMask);
  }

  public static void main(String[] args) {
    Y y = new Y();
  }
}
