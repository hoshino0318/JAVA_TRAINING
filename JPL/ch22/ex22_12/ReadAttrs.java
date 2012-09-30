package ch22.ex22_12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.regex.*;
import java.util.Scanner;

class ReadAttrs {
  public static Attributed readAttrs(Reader source) {
    Scanner in = new Scanner(source);
    AttributedImpl attrs = new AttributedImpl();
    String exp = "(.+?)=(.+?)";
    Pattern pat = Pattern.compile(exp);
    while (in.hasNext(pat)) {
      in.next(pat);
      String name, val;
      MatchResult match = in.match();
      name = match.group(1);
      val  = match.group(2);
      attrs.add(new Attr(name, val));
    }
    return attrs;
  }

  public static void main(String[] args)
      throws IOException {
    if (args.length == 0) {
      System.err.println("needs input files");
      System.exit(1);
    }

    for (String fpath : args) {
      BufferedReader in = null;
      try {
        in = new BufferedReader(new FileReader(fpath));
        Attributed attrs = readAttrs(in);
        Iterator<Attr> it = attrs.attrs();
        while (it.hasNext()) {
          System.out.println(it.next());
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
