package ch03.ex03_10;

class LinkedList<T> implements Cloneable {
  private T item; 
  private LinkedList<T> nextNode;
  
  LinkedList(T item) {
    this(item, null);
  }
  
  LinkedList(T item, LinkedList<T> nextNode) {
    this.item = item;
    this.nextNode = nextNode;
  }
  
  T getItem() {
    return item;
  }

  LinkedList<T> getNextNode() {
    return nextNode;
  }
  
  void setNextNode(LinkedList<T> nextNode) {
    this.nextNode = nextNode;
  }
  
  int getListLength() {
    if (nextNode != null) {
      return 1 + nextNode.getListLength();
    } else {
      return 1;
    }
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
  
  public LinkedList<T> clone() {
    try {
      return (LinkedList<T>) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.toString());
    }
  }
}
