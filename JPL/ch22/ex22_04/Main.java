package ch22.ex22_04;

class Main {
  public static void main(String[] args) {
    ObservableAttributed attributed = new ObservableAttributed();
    //AttributedObserver watcher = new AttributedObserver(attributed);
    new AttributedObserver(attributed);

    attributed.add(new Attr("obj1", "apple"));
    attributed.add(new Attr("obj2", "orange"));
    attributed.add(new Attr("obj3", "banana"));

    attributed.remove("obj2");
  }
}
