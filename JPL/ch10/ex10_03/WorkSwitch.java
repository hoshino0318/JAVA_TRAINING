package ch10.ex10_03;

class WorkSwitch {
  public static boolean isWorkingOn(Week week) {
    switch (week) {
    case SUN: case MON: case FRI: case SAT:
      return false;
    case TUE: case WED: case THR:
      return true;
    default :
      throw new IllegalStateException("week=" + week);
    }
  }
}
