package ch14.ex14_09;

class MyThreadGroup {
  static ThreadGroup addThreadGroup(ThreadGroup parent, String name) {
    return new ThreadGroup(parent, name);
  }

  static void addThread(ThreadGroup parent, String name) {
    new Thread(parent, name) {
      public void run() {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          return;
        }
      }
    }.start();
  }

  static void showTreadGroup(final ThreadGroup grp) {
    new Thread() {
      public void run() {
        for (;;) {
          try {
            System.out.println(grp.getName());
            MyThreadGroup.show(grp, 1);
            Thread.sleep(1000);
          } catch (InterruptedException e)  {
            return;
          }
        }
      }
    }.start();
  }

  private static final void show(ThreadGroup grp, int layer) {
    ThreadGroup[] threadGroups = new ThreadGroup[1000];
    Thread[] threads = new Thread[1000];

    grp.enumerate(threadGroups, false);
    grp.enumerate(threads, false);

    for (ThreadGroup thg : threadGroups) {
      if (thg != null) {
        for (int i = 0; i < layer; ++i)
          System.out.print("    ");

        System.out.println(thg.getName());
        show(thg, layer + 1);
      }
    }

    for (Thread thread : threads) {
      if (thread != null) {
        for (int i = 0; i < layer; ++i)
          System.out.print("    ");

        System.out.println(thread.getName());
      }
    }
  }
}
