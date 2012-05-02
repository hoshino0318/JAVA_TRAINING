package ch05.ex05_02;

import java.util.*;

class BankAccount {
  private long number;     // 口座番号
  private long balance;    // 現在の残高 (単位はセント)
  private Action lastAct;  // 最後に行われた処理
  private History history; // 最後に行われた処理 10 回
  
  BankAccount(long number, long balance) {
    this.number = number;
    this.balance = balance;
    this.history = new History();
  }
  
  public void deposit(long amount) {
    balance += amount;
    lastAct = new Action("deposit", amount);
    history.add(lastAct);
  }
  
  public void withdrow(long amount) {
    balance -= amount;
    lastAct = new Action("withdrow", amount);
    history.add(lastAct);
  }
  
  public void transfer(BankAccount other, long amount) {
    other.withdrow(amount);
    deposit(amount);
    lastAct = this.new Action("transfer", amount);    
    other.lastAct = other.new Action("transfer", amount);
    history.add(lastAct);
    other.history.add(other.lastAct);
  }
  
  public History getHistory() {
    return history.clone();
  }
  
  public class Action {
    private String act;
    private long amount;
    
    Action(String act, long amount) {
      this.act = act;
      this.amount = amount;
    }
    
    public String toString() {
      // identify our enclosing account
      return number + ": " + act + " " + amount;
    }
  }
  
  class History implements Cloneable {        
    private List<Action> list = new LinkedList<Action>();
    private int seekPos = 0;
    
    public static final int MAX_HISTORY_SIZE = 10;
    
    void add(Action action) {
      list.add(action);
      if (list.size() > MAX_HISTORY_SIZE) {
        list.remove(0);
      }
    }
    
    Action next() {
      if (hasNext())
        return list.get(seekPos++);
      else
        return null;
    }
    
    boolean hasNext() {
      return seekPos < list.size();
    }
    
    public History clone() {
      try {
        History nObj = (History) super.clone();
        nObj.list = new LinkedList<Action>(list);                             
        return nObj;
      } catch (CloneNotSupportedException e) {
        throw new InternalError(e.toString());
      }
    }
  }
}
