package ch14.ex14_03;

class Main {
  public static void main(String[] args) {
    MyData data = new MyData();
    new Thread(data).start();
    new Thread(data).start();
    new Thread(data).start();
    System.out.println("end");
  }
}
