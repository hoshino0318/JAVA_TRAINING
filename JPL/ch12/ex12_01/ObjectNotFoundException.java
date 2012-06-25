package ch12.ex12_01;

class ObjectNotFoundException extends Exception {
  public final Object obj;

  public ObjectNotFoundException(Object obj) {
    super("Object is not found");
    this.obj = obj;
  }
}
