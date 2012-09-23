package ch21.ex21_07;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;

public class StackTest {
  private Stack<String> stack;
  private static final int STACK_SIZE = 5;

  @Before
  public void setUp() throws Exception {
    stack = new Stack<String>();
    for (int i = 1; i <= STACK_SIZE; ++i)
      stack.push(String.valueOf(i));
  }

  @Test
  public void testPush() {
    assertThat(stack.push("6"), is("6"));
  }

  @Test
  public void testPop() {
    stack.push("10");
    assertThat(stack.pop(), is("10"));
  }

  @Test
  public void testPopRemoveTop() {
    stack.pop();
    assertThat(stack.pop(), is("4"));
  }

  @Test(expected = EmptyStackException.class)
  public void testPopEmptyStackException() {
    for (int i = 0; i < STACK_SIZE; ++i)
      stack.pop();
    stack.pop();
  }

  @Test
  public void testPeek() {
    assertThat(stack.peek(), is("5"));
  }

  @Test
  public void testPeekNoRemoveTop() {
    stack.peek();
    assertThat(stack.peek(), is("5"));
  }

  @Test(expected = EmptyStackException.class)
  public void testPeekEmptyStackException() {
    for (int i = 0; i < STACK_SIZE; ++i)
      stack.pop();
    stack.peek();
  }

  @Test
  public void testEmptyReturnFalse() {
    assertThat(stack.empty(), is(false));
  }

  @Test
  public void testEmptyReturnTrue() {
    for (int i = 0; i < STACK_SIZE; ++i)
      stack.pop();
    assertThat(stack.empty(), is(true));
  }

  @Test
  public void testSearch() {
    assertThat(stack.search("4"), is(2));
  }

  @Test
  public void testSearchReturnMinusOne() {
    assertThat(stack.search("100"), is(-1));
  }

  @Test
  public void testSizeReturnDefaultStackSize() {
    assertThat(stack.size(), is(STACK_SIZE));
  }

  @Test
  public void testSizeAfterPop() {
    stack.pop();
    assertThat(stack.size(), is(STACK_SIZE-1));
  }
}
