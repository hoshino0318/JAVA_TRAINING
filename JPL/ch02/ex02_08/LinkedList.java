package ch02.ex02_08;

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
}
