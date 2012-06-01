package ch10.ex10_03;

class WorkIfElse {
  public static boolean isWorkingOn(Week week) {
    if (week == Week.SUN || week == Week.MON || week == Week.FRI || week == Week.SAT) {
      return false;
    } else if (week == Week.TUE || week == Week.WED || week == Week.THR) {
      return true;
    } else {
      throw new IllegalStateException("week=" + week);
    }
  }
}
