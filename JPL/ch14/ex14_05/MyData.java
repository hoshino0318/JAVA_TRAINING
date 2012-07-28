package ch14.ex14_05;

class MyData implements Runnable {
  private static long data = 0;
  private static final Object lockObj = new Object();

  @Override
  public void run() {
    for (;;)
      addPrintSubtract();
  }

  private static void addPrintSubtract() {
    synchronized (MyData.lockObj) {
      MyData.data++;
      System.out.println(MyData.data);
      MyData.data--;
    }
  }
}
