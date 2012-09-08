package ch20.ex20_05;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;

class FindString {
  public static void main(String[] args)
      throws IOException
  {
    if (args.length != 2)
      throw new IllegalArgumentException("need string and file");

    String match = args[0];
    FileReader fileIn = new FileReader(args[1]);
    LineNumberReader in = new LineNumberReader(fileIn);
    String line;
    while ((line = in.readLine()) != null) {
      if (line.indexOf(match) != -1) {
        int lineNum = in.getLineNumber();
        System.out.println(lineNum + ": \"" + line + "\"");
      }
    }
  }
}
