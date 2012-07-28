package ch14.ex14_04;

class MyData implements Runnable {
  private static long data = 0;

  @Override
  public void run() {
    for (;;)
      addAndPrint();
  }

  private static synchronized void addAndPrint() {
    MyData.data++;
    System.out.println(MyData.data);
  }
}
