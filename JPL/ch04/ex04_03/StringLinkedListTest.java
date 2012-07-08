package ch04.ex04_03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringLinkedListTest {
  private LinkedList<String> sLinkedListA;
  private LinkedList<String> sLinkedListB;
  private LinkedList<String> sLinkedListC;

  @Before
  public void setUp() throws Exception {
    sLinkedListA = new StringLinkedList("A");
    sLinkedListB = new StringLinkedList("B");
    sLinkedListC = new StringLinkedList("C");

    sLinkedListA.setNext(sLinkedListB);
    sLinkedListB.setNext(sLinkedListC);
  }

  @Test
  public void testGetItemA() {
    String expected = "A";
    assertEquals(expected, sLinkedListA.getItem());
  }

  @Test
  public void testGetItemB() {
    String expected = "B";
    assertEquals(expected, sLinkedListA.next().getItem());
  }

  @Test
  public void testGetListLength() {
    int expected = 3;
    assertEquals(expected, sLinkedListA.getListLength());
  }

}
