package interpret.test;

public class TestTarget {
  final private int testData1;
  private int testData2;
  public TestTarget() {
    this(0, 0);
  }

  public TestTarget(int testData1, int testData2) {
    this.testData1 = testData1;
    this.testData2 = testData2;
  }

  public int getTestData1() {
    return testData1;
  }

  public int getTestData2() {
    return testData2;
  }
}
