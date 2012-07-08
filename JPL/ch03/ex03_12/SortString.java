package ch03.ex03_12;

public class SortString extends SortHarness {

  public SortString(String[] values) {
    super(values);
  }

  @Override
  public boolean compare(int i, int j) {
    String i_str = (String)probe(i);
    String j_str = (String)probe(j);

    if (i_str.length() > j_str.length()) return true;
    return false;
  }
}
