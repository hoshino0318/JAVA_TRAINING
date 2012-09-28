package ch22.ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

class ObservableAttributed extends Observable implements Attributed {
  private Map<String, Attr> attrTable =
    new HashMap<String, Attr>();

  @Override
  public void add(Attr newAttr) {
    attrTable.put(newAttr.getName(), newAttr);
    setChanged();
    notifyObservers(newAttr);
  }

  @Override
  public Attr find(String name) {
    return attrTable.get(name);
  }

  @Override
  public Attr remove(String name) {
    Attr removedAttr = attrTable.remove(name);
    setChanged();
    notifyObservers(removedAttr);
    return removedAttr;
  }

  @Override
  public Iterator<Attr> attrs() {
    return attrTable.values().iterator();
  }

  public Iterator<Attr> iterator() {
    return attrs();
  }

  public int size() {
    return attrTable.size();
  }
}
