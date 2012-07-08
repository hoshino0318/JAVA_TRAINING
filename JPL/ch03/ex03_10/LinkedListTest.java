package ch03.ex03_10;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testGetNextNode() {
    LinkedList<Integer> llist = new LinkedList<Integer>(0);

    assertNull(llist.getNextNode());
  }

  @Test
  public void testGetItem() {
    LinkedList<Integer> llist = new LinkedList<Integer>(100);

    assertEquals((Integer)llist.getItem(), (Integer)100);
  }

  @Test
  public void testToString() {
    LinkedList<Integer> node1 = new LinkedList<Integer>(100);
    LinkedList<Integer> node2 = new LinkedList<Integer>(0);
    node1.setNextNode(node2);
    String expected  = "100 => 0";

    assertEquals(expected, node1.toString());
  }

  @Test
  public void testGetListLength1() {
    LinkedList<Integer> node1 = new LinkedList<Integer>(100);
    LinkedList<Integer> node2 = new LinkedList<Integer>(0);
    LinkedList<Integer> node3 = new LinkedList<Integer>(15);
    node1.setNextNode(node2);
    node2.setNextNode(node3);

    int expectedLength = 3;

    assertEquals(expectedLength, node1.getListLength());
  }

  @Test
  public void testGetListLength2() {
    LinkedList<Integer> node1 = new LinkedList<Integer>(100);
    LinkedList<Integer> node2 = new LinkedList<Integer>(0);
    LinkedList<Integer> node3 = new LinkedList<Integer>(15);
    node1.setNextNode(node2);
    node2.setNextNode(node3);

    int expectedLength = 2;

    assertEquals(expectedLength, node2.getListLength());
  }

  @Test
  public void testClone1() {
    LinkedList<Vehicle> node1 = new LinkedList<Vehicle>(new Vehicle("Alice"));
    LinkedList<Vehicle> node2 = new LinkedList<Vehicle>(new Vehicle("Bob"));
    node1.setNextNode(node2);

    LinkedList<Vehicle> node4 = node1.clone();

    assertSame(node1.getNextNode(), node4.getNextNode());
  }

  @Test
  public void testClone2() {
    LinkedList<Vehicle> node1 = new LinkedList<Vehicle>(new Vehicle("Alice"));
    LinkedList<Vehicle> node2 = new LinkedList<Vehicle>(new Vehicle("Bob"));
    node1.setNextNode(node2);

    LinkedList<Vehicle> node4 = node1.clone();
    node1 = null;

    assertNotNull(node4.getNextNode());
  }

  @Test
  public void testClone3() {
    LinkedList<Vehicle> node1 = new LinkedList<Vehicle>(new Vehicle("Alice"));
    LinkedList<Vehicle> node2 = new LinkedList<Vehicle>(new Vehicle("Bob"));
    node1.setNextNode(node2);

    LinkedList<Vehicle> node4 = node1.clone();
    node1.getItem().changeOwner("Yamada");
    String owner1 = node1.getItem().getOwner();
    String owner2 = node4.getItem().getOwner();

    assertEquals(owner1, owner2);
  }
}
