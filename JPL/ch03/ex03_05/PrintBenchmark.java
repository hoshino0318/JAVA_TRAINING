package ch03.ex03_05;

public class PrintBenchmark extends Benchmark {
  @Override
  void benchmark() {
    System.out.println();
  }

  public static void main(String[] args) {
    int count = Integer.parseInt(args[0]);
    long time = new PrintBenchmark().repeat(count);
    System.out.println(count + " methods in " +
        time + " nanoseconds");
  }
}
