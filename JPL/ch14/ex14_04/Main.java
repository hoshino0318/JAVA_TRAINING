package ch14.ex14_04;

class Main {
  public static void main(String[] args) {
    MyData data = new MyData();
    new Thread(data).start();
    new Thread(data).start();
    new Thread(data).start();
    System.out.println("main ends");
  }
}
