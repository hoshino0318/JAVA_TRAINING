package ch03.ex03_07;

public class ColorAttr extends Attr {
  private ScreenColor myColor; // 変色された色

  public ColorAttr(String name, Object object) {
    super(name, object);
    decodeColor();
  }

  public ColorAttr(String name) {
    this(name, "transparent");
  }

  public ColorAttr(String name, ScreenColor value) {
    super(name, value.toString());
    myColor = value;
  }

  public Object setValue(Object newValue) {
    // スーパークラスの setValue を最初に行う
    Object retval = super.setValue(newValue);
    decodeColor();
    return retval;
  }

  /** 値を記述ではなく ScreenColor に設定する */
  public ScreenColor setValue(ScreenColor newValue) {
    // スーパークラスの setValue を最初に行う
    super.setValue(newValue.toString());
    ScreenColor oldValue = myColor;
    myColor = newValue;
    return oldValue;
  }

  /** 変換された ScreenColor オブジェクトを返す */
  public ScreenColor getColor() {
    return myColor;
  }

  /** getValue() で得られる記述から ScreenColor を設定する */
  protected void decodeColor() {
    if (getValue() == null)
      myColor = null;
    else
      myColor = new ScreenColor(getValue());
  }

  public boolean equals(ColorAttr colorAttr) {
    return super.equals(colorAttr) && myColor.equals(colorAttr.myColor);
  }

  public int hashCode() {
    return super.hashCode() + myColor.hashCode();
  }
}
