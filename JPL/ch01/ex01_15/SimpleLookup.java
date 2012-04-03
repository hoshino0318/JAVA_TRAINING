package ch01.ex01_15;

import java.util.List;
import java.util.ArrayList;

class SimpleLookup implements SubLookup {
  private List<String> names;
  private List<Object> values;
  
  SimpleLookup() {
    names = new ArrayList<String>();
    values = new ArrayList<Object>();
  }

  @Override
  public Object find(String name) {
    int index = names.indexOf(name);
    if (index == -1) {
      return null; // 見つからなかった   
    }
    
    return values.get(index);
  }

  @Override
  public void add(String name, Object value) {
    names.add(name);
    values.add(value);
  }

  @Override
  public Object remove(String name) {
    int index = names.indexOf(name);
    if (index == -1) {
      return null; // 削除対象が見つからなかった
    }
    
    names.remove(index);    
    return values.remove(index);
  }
}
