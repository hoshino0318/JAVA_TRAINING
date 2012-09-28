package ch22.ex22_04;

import java.util.Observable;
import java.util.Observer;

class AttributedObserver implements Observer {
  private ObservableAttributed attributed;
  private int prevAttributedSize;

  AttributedObserver(ObservableAttributed attributed) {
    this.attributed = attributed;
    prevAttributedSize = attributed.size();
    attributed.addObserver(this);
  }

  @Override
  public void update(Observable attributed, Object attrObj) {
    if (attributed != this.attributed)
      throw new IllegalArgumentException();

    Attr attr = (Attr)attrObj;
    if (this.attributed.size() > prevAttributedSize) {
      System.out.println("Attr " + attr + " is added.");
    } else if (this.attributed.size() < prevAttributedSize) {
      System.out.println("Attr " + attr + " is removed.");
    }
    prevAttributedSize = this.attributed.size();
  }
}
