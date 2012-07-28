package ch14.ex14_06;

class MessageDisplay implements Runnable {
  private int waitCount;
  private int limit;
  private String msg;

  MessageDisplay(int limit, String msg) {
    if (limit <= 0)
      throw new IllegalArgumentException("At least, limit must be 1.");
    waitCount = 0;
    this.limit = limit;
    this.msg = msg;
  }

  @Override
  public void run() {
    for (;;) {
      try {
        msgDisplay();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void countUp() {
    waitCount++;
    notifyAll();
  }

  private synchronized void msgDisplay() throws InterruptedException {
    while (waitCount != limit)
      wait();


    System.out.println(msg);
    waitCount = 0;
  }
}
