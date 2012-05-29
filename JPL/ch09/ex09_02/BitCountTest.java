package ch09.ex09_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BitCountTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testBitCount1() {
    int val = 3;
    int expected = 2;
    
    assertEquals(expected, BitCount.bitCount(val));
  }
  
  @Test
  public void testBitCount2() {
    int val = 16;
        
    assertEquals(Integer.bitCount(val), BitCount.bitCount(val));
  }
  
  @Test
  public void testBitCount3() {
    int val = Integer.MAX_VALUE;
    
    assertEquals(Integer.bitCount(val), BitCount.bitCount(val));
  }
  
  @Test
  public void testBitCount4() {
    int val = Integer.MIN_VALUE;
    
    assertEquals(Integer.bitCount(val), BitCount.bitCount(val));
  }
  
  

}
