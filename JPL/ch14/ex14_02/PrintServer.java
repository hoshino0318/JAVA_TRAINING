package ch14.ex14_02;

import java.lang.Math;

class PrintServer implements Runnable {
  private final PrintQueue requests = new PrintQueue();
  private static final String idStr;

  static {
    idStr = String.valueOf(Math.random());
  }

  public PrintServer() {
    Thread thread = new Thread(this);
    thread.setName(idStr);
    thread.start();
  }

  public void print(PrintJob job) {
    requests.add(job);
  }

  @Override
  public void run() {
    String currentThreadName = Thread.currentThread().getName();

    if (!currentThreadName.equals(idStr)) {
      System.out.println("Permission Denied");
      return;
    }

    for (;;)
      realPrint(requests.remove());
  }

  private void realPrint(PrintJob job) {
    try {
      System.out.println("real print!!");
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      return;
    }
  }
}
