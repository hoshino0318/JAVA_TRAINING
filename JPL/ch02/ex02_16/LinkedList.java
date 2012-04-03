package ch02.ex02_16;

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
  
  int getListLength() {
    int ret = 1;
    
    LinkedList nextNode = this.nextNode;
    for  ( ; nextNode != null; nextNode = nextNode.getNextNode(), ++ret);
    
    return ret;
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
