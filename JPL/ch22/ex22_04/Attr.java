package ch22.ex22_04;

class Attr {
  private final String name;
  private Object value = null;

  Attr(String name) {
    this.name = name;
  }

  Attr(String name, Object value) {
    this.name = name;
    this.value = value;
  }

  String getName() {
    return name;
  }

  Object getValue() {
    return value;
  }

  Object setValue(Object newValue) {
    Object oldVal = value;
    value = newValue;
    return oldVal;
  }

  @Override
  public String toString() {
    return name + "='" + value + "'";
  }
}
