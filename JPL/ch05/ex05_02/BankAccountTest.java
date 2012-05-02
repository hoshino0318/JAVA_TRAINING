package ch05.ex05_02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
  BankAccount bankAccount1;
  BankAccount bankAccount2;

  @Before
  public void setUp() throws Exception {
    bankAccount1 = new BankAccount(1, 100);
    bankAccount2 = new BankAccount(1, 100);
    
    for (int i = 1; i <= 10; ++i) 
      bankAccount1.deposit(i);
  }

  @Test
  public void testHistory1() {
    BankAccount.History history = bankAccount1.getHistory();
    
    String expected = "";
    for (int i = 1; i <= 10; ++i)
      expected += "1: deposit " + i;
    
    String actual = "";
    while (history.hasNext()) {
      actual += history.next().toString();
    }
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testHistory2() {
    BankAccount.History history = bankAccount1.getHistory();
    bankAccount1.deposit(11);
    
    String expected = "";
    for (int i = 1; i <= 10; ++i)
      expected += "1: deposit " + i;
    
    String actual = "";
    while (history.hasNext()) {
      actual += history.next().toString();
    }
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testHistory3() {
    bankAccount1.transfer(bankAccount2, 100);
    BankAccount.History history = bankAccount1.getHistory();
        
    String expected = "";
    for (int i = 3; i <= 10; ++i)
      expected += "1: deposit " + i;
    expected += "1: deposit " + 100; 
    expected += "1: transfer " + 100;
    
    String actual = "";
    while (history.hasNext()) {
      actual += history.next().toString();
    }

    System.out.println(expected);
    System.out.println(actual);
    assertEquals(expected, actual);
  }
  
  
}
