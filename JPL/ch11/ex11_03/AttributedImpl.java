package ch11.ex11_03;

import java.util.*;
import java.util.Iterator;

class AttributedImpl<T> implements Attributed<T>, Iterable<Attr<T>> {
  protected Map<String, Attr<T>> attrTable =
      new HashMap<String, Attr<T>>();

  @Override
  public void add(Attr<T> newAttr) {
    attrTable.put(newAttr.getName(), newAttr);
  }

  @Override
  public Attr<T> find(String name) {
    return attrTable.get(name);
  }

  @Override
  public Attr<T> remove(String name) {
    return attrTable.remove(name);
  }

  @Override
  public Iterator<Attr<T>> attrs() {
    return attrTable.values().iterator();
  }

  @Override
  public Iterator<Attr<T>> iterator() {
    return attrs();
  }

}
