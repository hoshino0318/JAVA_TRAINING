package ch20.ex20_04;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.StringBuilder;

class LineReader extends FilterReader {
  LineReader(Reader in) {
    super(in);
  }

  char[] readLine() throws IOException {
    StringBuilder strBuilder = new StringBuilder();
    int c;
    while ((c = super.read()) != -1) {
      strBuilder.append((char)c);
      if (c == '\n')
        break;
    }

    if (strBuilder.toString().equals(""))
      return null;
    return strBuilder.toString().toCharArray();
  }

  public static void main(String[] args)
      throws IOException {
    if (args.length == 0)
      System.exit(1);

    FileReader fileReader = null;
    try {
      fileReader = new FileReader(args[0]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    LineReader lineReader = new LineReader(fileReader);
    char[] line;
    while ((line = lineReader.readLine()) != null) {
      System.out.print(line);
    }
  }
}
