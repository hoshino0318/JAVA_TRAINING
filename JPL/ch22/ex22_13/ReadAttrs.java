package ch22.ex22_13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

class ReadAttrs {
  public static Attributed readAttrs(Reader source)
      throws IOException {
    Scanner in = new Scanner(source);
    in.useDelimiter("\\s+|=]");
    AttributedImpl attrs = new AttributedImpl();
    Attr attr = null;
    while (in.hasNext()) {
      String next = in.next();
      if (attr == null) {
        if (next.equals(""))
          throw new IOException("bad format");
        attr = new Attr(next);
        attrs.add(attr);
        in.useDelimiter("\\s*");
      } else {
        if (next.equals("=")) {
          in.useDelimiter("\\s+");
        } else {
          attr.setValue(next);
          attr = null;
          in.useDelimiter("\\s+|=");
        }
      }
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
