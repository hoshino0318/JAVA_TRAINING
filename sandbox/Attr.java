
/**
 * An <code>Attr</code> object defines an attribute as a
 * name/value pair, where the name is <code>String</code>
 * and the value an arbitrary <code>Object</code>.
 *
 * @version 1.1
 * @author Earth
 * @since 1.0
 */
class Attr {
  /**  The attribute name */
  private final String name;
  /** The attribute value. */
  private Object value = null;

  /**
   * Creates a new attribute with the given name and an
   * initial value of <code>null</code>.
   * @see Attr#Attr(String,Object)
   */
  public Attr(String name) {
    this.name = name;
  }
  /**
   * Creates a new attribute with the given name and
   * initial value.
   * @see Attr#Attr(String)
   */
  public Attr(String name, Object value) {
    this.name = name;
    this.value = value;
  }

  /** Returns this attribute's name. */
  public String getName() {
    return name;
  }

  /** Returns this attribute's values. */
  public Object getValue() {
    return value;
  }

  /**
   * Sets the value of this attribute. Changes the
   * value returned by calls to {@link #getValue}.
   * @param newValue The new value for the attribute.
   * @return The original value.
   * @see #getValue()
   */
  public Object setValue(Object newValue) {
    Object oldVal = value;
    value = newValue;
    return oldVal;
  }

  /**
   * Returns a string of the form <code>name=value</code>
   */
  public String toString() {
    return name + "='" + value + "'";
  }

}
