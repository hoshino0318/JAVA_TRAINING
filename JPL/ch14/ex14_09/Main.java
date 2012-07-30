package ch14.ex14_09;

class Main {
  public static void main(String[] args) {
    ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
    MyThreadGroup.showTreadGroup(mainThreadGroup);

    ThreadGroup myThreadGroup = MyThreadGroup.addThreadGroup(mainThreadGroup, "myThreadGroup");

    try {
      MyThreadGroup.addThread(myThreadGroup, "Thread1");
      Thread.sleep(1000);
      MyThreadGroup.addThread(mainThreadGroup, "Thread2");
      Thread.sleep(1000);
      MyThreadGroup.addThread(myThreadGroup, "Thread3");
    } catch (InterruptedException e) {
      return;
    }
  }
}
