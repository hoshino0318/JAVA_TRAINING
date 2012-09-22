package ch21.ex21_04;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ShortStringsTest {
  private ShortStrings shortStrings;

  @Before
  public void setUp() throws Exception {
    List<String> list = new LinkedList<String>();
    for (int i = 1; i <= 10; ++i) {
      StringBuilder strBuilder = new StringBuilder();
      for (int j = 0; j < i; ++j)
        strBuilder.append(String.valueOf(i));

      list.add(strBuilder.toString());
    }
    shortStrings = new ShortStrings(list.listIterator(), 5);
  }

  @Test
  public void testHasNext() {
    assertThat(shortStrings.hasNext(), is(true));
  }

  @Test
  public void testNextFirstElement() {
    assertThat(shortStrings.next(), is("1"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testNextNoSuchElement() {
    while(shortStrings.hasNext())
      shortStrings.next();
    shortStrings.next();
  }

  @Test
  public void testHasPrevious() {
    while(shortStrings.hasNext())
      shortStrings.next();
    assertThat(shortStrings.hasPrevious(), is(true));
  }

  @Test
  public void testPrevious() {
    while(shortStrings.hasNext())
      shortStrings.next();
    assertThat(shortStrings.previous(), is("55555"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testPreviousNoSuchElement() {
    shortStrings.previous();
  }

  @Test
  public void testNextIndex() {
    assertThat(shortStrings.nextIndex(), is(0));
  }

  @Test
  public void testNextIndexSecondElement() {
    shortStrings.next();
    assertThat(shortStrings.nextIndex(), is(1));
  }

  @Test
  public void testNextIndexReturnSize() {
    while (shortStrings.hasNext())
      shortStrings.next();
    assertThat(shortStrings.nextIndex(), is(5));
  }
}
