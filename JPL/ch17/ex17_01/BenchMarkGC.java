package ch17.ex17_01;

class BenchMarkGC {
  private static final int MAX_LIST_SIZE = 500000;

  private static void printFreeMemory() {

    new Thread(new Runnable() {
      public void run() {
        Runtime rt = Runtime.getRuntime();
        for (;;) {
          try {
            Thread.sleep(1000);
            System.out.println("free memory: " + rt.freeMemory());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();

  }

  public static void main(String[] args) {
    Runtime rt = Runtime.getRuntime();
    System.out.println("start: " + rt.freeMemory());

    /* データの生成
     * (わざとオブジェクトを生成) */
    Integer[] data = new Integer[MAX_LIST_SIZE];
    for (int i = 0; i < MAX_LIST_SIZE; ++i)
      data[i] = new Integer(i);

    System.out.println("after create objects: " + rt.freeMemory());

    printFreeMemory();

    for (int i = 0; i < MAX_LIST_SIZE; ++i) {
      data[i] = null;
      try {
        rt.gc();
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
