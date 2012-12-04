package ch22.ex22_06;

import java.util.Random;

class Gaussian {
  private static final int EXPERIMENT_NUM = 50000000;
  private static final int PERIOD_NUM = 20;
  private static final double PERIOD = 1.0 / PERIOD_NUM;
  private static final int STEP = 100;

  public static void main(String[] args) {
    Random random = new Random();

    //SortedMap<Integer, Integer> counter = new TreeMap<Integer, Integer>();
    int[] counter = new int[PERIOD_NUM];
    for (int i = 0; i < counter.length; ++i)
      counter[i] = 0;

    for (int i = 0; i < EXPERIMENT_NUM; ++i) {
      double d = random.nextGaussian();
      for (int j = 0; j < counter.length; ++j) {
        if (PERIOD * j <= d && d < PERIOD * (j + 1)) {
          counter[j]++;
          break;
        }
      }
    }

    int max = 0;
    for (int i = 0; i < counter.length; ++i)
      max = (max < counter[i]) ? counter[i] : max;

    for (int i = 0; i < counter.length; ++i) {
      int width = max / STEP;
      for (int j = 0; j < counter[i] / width; ++j)
        System.out.print('*');
      System.out.println();
    }
  }
}
