package ch14.ex14_06;

import java.util.ArrayList;
import java.util.Iterator;

class TimeCount implements Runnable {
  private long elapsedTime = 0;
  private ArrayList<MessageDisplay> msgDisplays = new ArrayList<MessageDisplay>();

  public void addMsgDisplay(MessageDisplay msgDisplay) {
    msgDisplays.add(msgDisplay);
  }

  public void notifyMsgDisplays() {
    Iterator<MessageDisplay> it = msgDisplays.iterator();
    while (it.hasNext()) {
      MessageDisplay m = it.next();
      m.countUp();
    }
  }

  @Override
  public void run() {
    for (;;) {
      try {
        Thread.sleep(1000);
        elapsedTime++;
        System.out.println("Time: " + elapsedTime);
        notifyMsgDisplays();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
