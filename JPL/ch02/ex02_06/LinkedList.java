package ch02.ex02_06;

class LinkedList {
  private Object item;
  private LinkedList nextNode;

  LinkedList(Object item) {
    this.item = item;
    this.nextNode = null;
  }

  LinkedList(Object item, LinkedList nextNode) {
    this.item = item;
    this.nextNode = nextNode;
  }

  Object getItem() {
    return item;
  }

  LinkedList getNextNode() {
    return nextNode;
  }

  void setNextNode(LinkedList nextNode) {
    this.nextNode = nextNode;
  }

  public static void main(String[] args) {
    /* 3 つのノードを作成 */
    LinkedList n1 = new LinkedList(new Vehicle(10, 10, "Alice"));
    LinkedList n2 = new LinkedList(new Vehicle(20, -10, "Bob"));
    LinkedList n3 = new LinkedList(new Vehicle(100, 400, "Chiris"));

    /* リンクを繋げる */
    n1.setNextNode(n2);
    n2.setNextNode(n3);

    /* リンクの内容を順番に表示 */
    LinkedList nextNode = n1;
    while (nextNode != null) {
      Vehicle v = (Vehicle)nextNode.getItem();
      v.printFields();
      nextNode = nextNode.getNextNode();
    }
  }
}
