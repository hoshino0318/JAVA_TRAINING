package ch22.ex22_05;

import java.util.TreeMap;
import java.util.SortedMap;

class Dices {
  short[] dices;
  SortedMap<Short, Integer> counter;

  Dices(int num) {
    if (num <= 0 || num > 6)
      throw new IllegalArgumentException("0 < dice num <= 6");

    dices = new short[num];
    for (int i = 0; i < dices.length; ++i)
      dices[i] = 1;
  }

  SortedMap<Short, Integer> getCalcResult() {
    if (counter == null)
      calculate();
    return counter;
  }

  private void calculate() {
    counter = new TreeMap<Short, Integer>();
    short limit = (short)(dices.length * 6);

    do {
      short s = sum();
      Integer c = counter.get(s);
      if (c == null) {
        counter.put(s, 1);
      } else {
        counter.put(s, c + 1);
      }
      next();
    } while (sum() != limit);
    counter.put(limit, 1);
  }

  private short sum() {
    short s = 0;
    for (short dice : dices)
      s += dice;
    return s;
  }

  private void next() {
    for (int i = 0; i < dices.length; ++i) {
      if (dices[i] == 6) {
        dices[i] = 1;
      } else {
        dices[i] += 1;
        break;
      }
    }
  }
}
