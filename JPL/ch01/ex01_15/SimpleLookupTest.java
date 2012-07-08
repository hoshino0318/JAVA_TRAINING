package ch01.ex01_15;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleLookupTest {
  SimpleLookup simpleLookup;

  @Before
  public void setUp() throws Exception {
    simpleLookup = new SimpleLookup();
  }

  @Test
  public void testAdd() {
    simpleLookup.add("alice", 1);
    simpleLookup.add("bob", 2);

    assertEquals(simpleLookup.find("bob"), 2);
  }

  @Test
  public void testFindFail() {
    simpleLookup.add("alice", 1);
    simpleLookup.add("bob", 2);

    assertEquals(simpleLookup.find("chiris"), null);
  }

  @Test
  public void testRemove() {
    simpleLookup.add("alice", 1);
    simpleLookup.add("bob", 2);
    simpleLookup.remove("alice");

    assertEquals(simpleLookup.find("alice"), null);
  }

}
