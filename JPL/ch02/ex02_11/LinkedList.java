package ch02.ex02_11;

class LinkedList {
  private Object item;
  private LinkedList nextNode;

  LinkedList(Object item) {
    this(item, null);
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

  public String toString() {
    String ret = "";

    ret += item.toString();
    if (nextNode != null) {
      ret += " => ";
      ret += nextNode.toString();
    }

    return ret;
  }
}
