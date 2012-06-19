package ch11.ex11_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.media.jai.codecimpl.fpx.StructuredStorage;

public class AttrTest {
  @Test
  public void testTypeArgument1() {
    Attr<String> strAttr = new Attr<String>("string", "string object");
    Class<String> expectedClass = String.class; 
    
    assertEquals(expectedClass, strAttr.getValue().getClass());
  }
  
  @Test
  public void testTypeArgument2() {
    Attr<Integer> intAttr = new Attr<Integer>("integer", 1);
    Class<Integer> expectedClass = Integer.class;
    
    assertEquals(expectedClass, intAttr.getValue().getClass());
  }
}
