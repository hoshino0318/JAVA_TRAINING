package ch22.ex22_11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class ReadCSV {
  static final int CELLS = 4;

  public static List<String[]> readCSVTable(Reader source)
      throws IOException {
    StreamTokenizer in = new StreamTokenizer(source);
    in.ordinaryChar(',');
    in.eolIsSignificant(true);
    List<String[]> vals = new ArrayList<String[]>();
    List<String> csv = new ArrayList<String>(); // csv データ一行
    while (in.nextToken() != StreamTokenizer.TT_EOF) {
      if (in.ttype == StreamTokenizer.TT_EOL) {
        vals.add(csv.toArray(new String[0]));
        csv = new ArrayList<String>();
      } else if (in.ttype == StreamTokenizer.TT_WORD) {
        csv.add(in.sval);
      }
    }
    return vals;
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
        List<String[]> vals = readCSVTable(in);

        for (String[] csv : vals) {
          for (int i = 0; i < csv.length; ++i)
            System.out.print(csv[i] + " ");
          System.out.println();
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
