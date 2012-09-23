package ch21.ex21_05;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchListTest {
  private ListIterator<String> listIterator;

  @Before
  public void setUp() throws Exception {
    int arraysSize = 10;
    String[][] arrays = new String[arraysSize][];
    for (int i = 1; i <= arraysSize; ++i) {
      String[] array = new String[i];
      for (int j = 0; j < i; ++j)
        array[j] = String.valueOf(i);
      arrays[i-1] = array;
    }
    List<String> list = new ArrayBunchList<String>(arrays);
    listIterator = list.listIterator();
  }

  @Test
  public void testHasNext() {
    assertThat(listIterator.hasNext(), is(true));
  }

  @Test
  public void testNextFirstElement() {
    assertThat(listIterator.next(), is("1"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testNextNoSuchElement() {
    while (listIterator.hasNext())
      listIterator.next();
    listIterator.next();
  }

  @Test
  public void testHasPrevious() {
    while (listIterator.hasNext())
      listIterator.next();
    assertThat(listIterator.hasPrevious(), is(true));
  }

  @Test
  public void testPrevious() {
    while (listIterator.hasNext())
      listIterator.next();
    assertThat(listIterator.previous(), is("10"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testPreviousNoSuchElement() {
    listIterator.previous();
  }

  @Test
  public void testNextIndex() {
    assertThat(listIterator.nextIndex(), is(0));
  }

  @Test
  public void testNextIndexReturnSize() {
    while (listIterator.hasNext())
      listIterator.next();
    assertThat(listIterator.nextIndex(), is(55));
  }

  @Test
  public void testPreviousIndex() {
    while (listIterator.hasNext())
      listIterator.next();
    assertThat(listIterator.previousIndex(), is(54));
  }

  @Test
  public void testPreviousReturnMinusOne() {
    assertThat(listIterator.previousIndex(), is(-1));
  }
}
