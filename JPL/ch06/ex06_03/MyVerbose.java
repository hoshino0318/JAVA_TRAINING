package ch06.ex06_03;

class MyVerbose implements Verbose {
  Level level;

  MyVerbose(Level level) {
    this.level = level;
  }

  @Override
  public void setVerbosity(Level level) {
    this.level = level;
  }

  @Override
  public Level getVerbosity() {
    return level;
  }
}
