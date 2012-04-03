package ch02.ex02_02;

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
  
  void setNextNode(LinkedList nextNode) {
    this.nextNode = nextNode;
  }
  
  LinkedList getNextNode() {
    return nextNode;
  }  
}
