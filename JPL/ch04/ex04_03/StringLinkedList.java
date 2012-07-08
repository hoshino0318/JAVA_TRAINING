package ch04.ex04_03;

public class StringLinkedList implements LinkedList<String> {
  private String item;
  private LinkedList<String> nextNode;

  public StringLinkedList(String item) {
    this(item, null);
  }

  public StringLinkedList(String item, LinkedList<String> nextNode) {
    this.item = item;
    this.nextNode = nextNode;
  }

  @Override
  public String getItem() {
    return item;
  }

  @Override
  public LinkedList<String> next() {
    return nextNode;
  }

  @Override
  public void setNext(LinkedList<String> nextNode) {
    this.nextNode = nextNode;
  }

  @Override
  public int getListLength() {
    if (nextNode == null)
      return 1;
    return 1 + nextNode.getListLength();
  }

  public String toString() {
    String ret = "";
    ret += item;
    if (nextNode != null) {
      ret += " => ";
      ret += nextNode.toString();
    }

    return ret;
  }
}
