package ch02.ex02_14;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testGetNextNode() {
    LinkedList llist = new LinkedList(0);

    assertEquals(llist.getNextNode(), null);
  }

  @Test
  public void testGetItem() {
    LinkedList llist = new LinkedList(100);

    assertEquals(llist.getItem(), 100);
  }

  @Test
  public void testToString() {
    LinkedList node1 = new LinkedList(100);
    LinkedList node2 = new LinkedList(0);
    node1.setNextNode(node2);
    String expected  = "100 => 0";

    assertEquals(node1.toString(), expected);
  }
}
