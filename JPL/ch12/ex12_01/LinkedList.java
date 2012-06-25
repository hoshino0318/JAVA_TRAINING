package ch12.ex12_01;

class LinkedList<E> implements Cloneable {
  private E item;
  private LinkedList<E> nextNode;

  LinkedList(E item) {
    this(item, null);
  }

  LinkedList(E item, LinkedList<E> nextNode) {
    this.item = item;
    this.nextNode = nextNode;
  }

  E getItem() {
    return item;
  }

  LinkedList<E> getNextNode() {
    return nextNode;
  }

  void setNextNode(LinkedList<E> nextNode) {
    this.nextNode = nextNode;
  }

  int getListLength() {
    int length = 0;

    for (LinkedList<E> node = this; node != null; length++, node = node.getNextNode());

    return length;
  }

  LinkedList<E> find(E item)
      throws ObjectNotFoundException
  {
    LinkedList<E> it = this;
    while (it != null) {
      if (it.item.equals(item)) {
        return it;
      }
      it = it.nextNode;
    }

    throw new ObjectNotFoundException(item);
  }

  @Override
  public String toString() {
    String ret = "";

    ret += item.toString();
    if (nextNode != null) {
      ret += " => ";
      ret += nextNode.toString();
    }

    return ret;
  }

  @Override
  public LinkedList<E> clone() {
    try {
      return (LinkedList<E>) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.toString());
    }
  }
}
