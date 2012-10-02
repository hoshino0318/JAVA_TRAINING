package ch22.ex22_04;

interface Attributed {
  public void add(Attr newAttr);
  public Attr find(String attrName);
  public Attr remove(String attrName);
  public java.util.Iterator<Attr> attrs();
}
