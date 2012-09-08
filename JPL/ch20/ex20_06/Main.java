package ch20.ex20_06;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

class Main {
  public static void main(String[] args) {
    Reader in = null;

    if (args.length != 1)
      throw new IllegalArgumentException("need file");

    try {
      in = new FileReader(args[0]);
    } catch (FileNotFoundException e) {
      System.err.println(args[0] + " not found");
      System.exit(1);
    }

    MyStreamTokenizer stk = new MyStreamTokenizer(in);

    try {
      stk.parse();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (ParseException e) {
      e.printStackTrace();
      System.exit(1);
    }

    stk.printVariables();
  }
}
