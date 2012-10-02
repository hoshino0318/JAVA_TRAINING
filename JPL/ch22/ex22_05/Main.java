package ch22.ex22_05;

import java.util.SortedMap;
import java.util.TreeMap;

class Main {
  private static final int DICE_NUM = 3;
  private static final int EXPERIMENT_NUM = 333333;

  public static void main(String[] args) {
    Dices dices = new Dices(DICE_NUM);
    SortedMap<Short, Integer> theoCounter = dices.getCalcResult();

    int sum = 0;
    for (int c : theoCounter.values())
      sum += c;

    System.out.println("Theoretical value");
    for (SortedMap.Entry<Short, Integer> entry : theoCounter.entrySet()) {
      System.out.println(entry.getKey() + " : " + (double)entry.getValue() / sum);
    }
    System.out.println();

    /* Experiment */
    SortedMap<Short, Integer> expCounter = new TreeMap<Short, Integer>();
    Dice dice = new Dice();
    for (int i = 0; i < EXPERIMENT_NUM; ++i) {
      short s = 0;
      for (int j = 0; j < DICE_NUM; ++j)
        s += dice.throwDice();
      Integer tmp = expCounter.get(s);
      if (tmp == null) {
        expCounter.put(s, 1);
      } else {
        expCounter.put(s, tmp + 1);
      }
    }

    System.out.println("Experiment value");
    for (SortedMap.Entry<Short, Integer> entry : expCounter.entrySet()) {
      System.out.println(entry.getKey() + " : " + (double)entry.getValue() / EXPERIMENT_NUM);
    }
  }
}
