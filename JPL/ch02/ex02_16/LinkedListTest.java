package ch02.ex02_16;

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

  @Test
  public void testGetListLength1() {
    LinkedList node1 = new LinkedList(100);
    LinkedList node2 = new LinkedList(0);
    LinkedList node3 = new LinkedList(15);
    node1.setNextNode(node2);
    node2.setNextNode(node3);

    int expectedLength = 3;

    assertEquals(node1.getListLength(), expectedLength);
  }

  @Test
  public void testGetListLength2() {
    LinkedList node1 = new LinkedList(100);
    LinkedList node2 = new LinkedList(0);
    LinkedList node3 = new LinkedList(15);
    node1.setNextNode(node2);
    node2.setNextNode(node3);

    int expectedLength = 2;

    assertEquals(node2.getListLength(), expectedLength);
  }
}
