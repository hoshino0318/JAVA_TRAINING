package ch14.ex14_03;

class MyData implements Runnable {
  private long data = 0;

  @Override
  public void run() {
    for (;;)
      addAndPrint();
  }

  private synchronized void addAndPrint() {
    data++;
    System.out.println(data);
  }
}
