package ch24.ex24_03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

class MyDateParse {
  private static final int[] styles = { DateFormat.FULL, DateFormat.LONG, DateFormat.MEDIUM, DateFormat.SHORT };
  private static final String[] styleStrs = { "FULL", "LONG", "MEDIUM", "SHORT" };
  private static final DateFormat[] dateFomatters;
  private static final DateFormat[] timeFomatters;
  private static final DateFormat[] dateTimeFomatters;

  static {
    dateFomatters = new DateFormat[styles.length];
    for (int i = 0; i < styles.length; i++) {
      dateFomatters[i] = DateFormat.getDateInstance(styles[i]);
    }

    timeFomatters = new DateFormat[styles.length];
    for (int i = 0; i < styles.length; i++) {
      timeFomatters[i] = DateFormat.getTimeInstance(styles[i]);
    }

    dateTimeFomatters = new DateFormat[styles.length * styles.length];
    for (int i = 0; i < styles.length; i++) {
      for (int j = 0; j < styles.length; j++) {
        int index = i * styles.length + j;
        dateTimeFomatters[index] = DateFormat.getDateTimeInstance(styles[i], styles[j]);
      }
    }
  }

  public static void main(String[] args) {
    printAllDateFormat("2012/12/01 10:10");

    for (String dateStr : args) {
      printAllDateFormat(dateStr);
    }

  }

  private static void printAllDateFormat(String dateStr) {
    for (int i = 0; i < dateFomatters.length; i++) {
      try {
        printDate(dateStr, dateFomatters[i]);
      } catch (ParseException e) {
        System.out.println("invalid date format: " + dateStr + " (style: " + styleStrs[i] + ")");
      }
    }
    System.out.println();

    for (int i = 0; i < timeFomatters.length; i++) {
      try {
        printDate(dateStr, timeFomatters[i]);
      } catch (ParseException e) {
        System.out.println("invalid date format: " + dateStr + " (style: " + styleStrs[i] + ")");
      }
    }
    System.out.println();

    for (int i = 0; i < dateTimeFomatters.length; i++) {
      try {
        printDate(dateStr, dateTimeFomatters[i]);
      } catch (ParseException e) {
        System.out.println("invalid date format: " + dateStr + " (style: " + styleStrs[i / 4] + " + " + styleStrs[i % 4] + ")");
      }
    }
  }

  private static void printDate(String dateStr, DateFormat formatter)
       throws ParseException {
    Date date = formatter.parse(dateStr);
    System.out.println(formatter.format(date));
  }


}
