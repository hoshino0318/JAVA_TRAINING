package ch14.ex14_06;

class Main {
  public static void main(String[] args) {
    TimeCount timeCount = new TimeCount();
    MessageDisplay msgDisplay1 = new MessageDisplay(15, "MSG 15!!!");
    MessageDisplay msgDisplay2 = new MessageDisplay(7, "MSG 7!!!");


    timeCount.addMsgDisplay(msgDisplay1);
    timeCount.addMsgDisplay(msgDisplay2);

    new Thread(msgDisplay1).start();
    new Thread(msgDisplay2).start();
    new Thread(timeCount).start();
  }
}
