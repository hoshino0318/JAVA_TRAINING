package ch11.ex11_02;

class Attr<T> {
  private final String name;
  private T value = null;
  
  public Attr(String name) {
    this.name = name;
  }
  
  public Attr(String name, T value) {
    this.name = name;
    this.value = value;
  }
  
  public String getName() {
    return name;
  }
  
  public T getValue() {
    return value;
  }
  
  public T setValue(T newValue) {
    T oldVal = value;
    value = newValue;
    return oldVal;
  }
  
  @Override
  public String toString() {
    return name + "='" + value + "'"; 
  }
}
