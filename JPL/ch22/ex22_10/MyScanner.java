package ch22.ex22_10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyScanner {
  //private static final String LINE_SEPARATOR_PATTERN =
      //"\r\n|[\n\r\u2028\u2029\u0085]";
  private static final String[] LINE_SEPARATOR_PATTERNS =
    {"\r\n", "\n", "\r", "\u2028", "\u2029", "\u0085"};
  private static final String COMMENT_PATTERN = "#.+";

  static List<String> readlines(Readable source) {
    Scanner in = new Scanner(source);
    //in.useDelimiter(LINE_SEPARATOR_PATTERN + "|#.+");
    //in.useDelimiter(LINE_SEPARATOR_PATTERN);
    //in.useDelimiter(LINE_SEPARATOR_PATTERN + "|#.+$");
    //in.useDelimiter("#.+\r\n" + "|#.+\n" + "|\n");
    String LINE_SEPARATOR_PATTERN =
        COMMENT_PATTERN + LINE_SEPARATOR_PATTERNS[0] + "|" + LINE_SEPARATOR_PATTERNS[0];
    for (int i = 1; i < LINE_SEPARATOR_PATTERNS.length; ++i) {
      LINE_SEPARATOR_PATTERN +=
          "|" + COMMENT_PATTERN + LINE_SEPARATOR_PATTERNS[i] + "|" + LINE_SEPARATOR_PATTERNS[i];
    }
    in.useDelimiter(LINE_SEPARATOR_PATTERN);
    List<String> lines = new ArrayList<String>();

    while (in.hasNext()) {
      String line = in.next();
      if (!line.isEmpty())
        lines.add(line);
    }

    return lines;
  }

  public static void main(String[] args)
      throws IOException {
    if (args.length == 0) {
      System.err.println("needs input files");
      System.exit(1);
    }

    for (String fpath : args) {
      FileReader in = null;
      try {
        in =  new FileReader(fpath);
        List<String> lines = readlines(in);
        for (String line : lines) {
          System.out.println(line.length() + " " + line);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (in != null)
          in.close();
      }
    }
  }
}
