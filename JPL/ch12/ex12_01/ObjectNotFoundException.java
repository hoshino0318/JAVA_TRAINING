package ch12.ex12_01;

class ObjectNotFoundException extends Exception {
  private static final long serialVersionUID = -6963250385015182434L;

  public final Object obj;

  public ObjectNotFoundException(Object obj) {
    super("Object is not found");
    this.obj = obj;
  }
}
