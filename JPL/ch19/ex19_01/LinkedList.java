package ch19.ex19_01;

/**
 * An <code>LinkedList</code> object defines an list,
 * which has an item and a next lisk node.
 * The item is an arbitrary <code>Object</code>.
 *
 * @version 1.0
 * @author Tatsuya Hoshino
 * @since 1.0
 */
class LinkedList {
  /** The item. */
  private Object item;
  /**
   * The next list node.
   * If the next node is <code>null</code>, this node is last. */
  private LinkedList nextNode;

  /**
   * Creates a new list node with the given item and
   * an initial next node of <code>null</>.
   * @see LinkedList#LinkedList(Object, LinkedList)
   */
  LinkedList(Object item) {
    this(item, null);
  }

  /**
   * Creates a new list node with the given item and
   * the initial next node.
   * @see LinkedList#LinkedList(Object)
   */
  LinkedList(Object item, LinkedList nextNode) {
    this.item = item;
    this.nextNode = nextNode;
  }

  /** Returns this list node's item. */
  Object getItem() {
    return item;
  }

  /** Returns this list node's next node. */
  LinkedList getNextNode() {
    return nextNode;
  }

  /**
   * Sets the next node of this list node. Changes the
   * next node returned by calls to {@link #getNextNode}}.
   * @param nextNode The next node for the list node.
   * @see #getNextNode()
   */
  void setNextNode(LinkedList nextNode) {
    this.nextNode = nextNode;
  }

  /**
   * Returns the total length of the linked list node.
   * @return The total list length.
   */
  int getListLength() {
    int length = 1;

    LinkedList nextNode = this.nextNode;
    for  ( ; nextNode != null; nextNode = nextNode.getNextNode(), ++length);

    return length;
  }

  /**
   * Returns a string of the form <code>item => nextItem => ... </code>.
   */
  @Override
  public String toString() {
    String str = "";

    str += item.toString();
    if (nextNode != null) {
      str += " => ";
      str += nextNode.toString();
    }

    return str;
  }
}
