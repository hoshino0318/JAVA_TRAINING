package ch21.ex21_01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineEvent;

class LineReader extends FilterReader {
  LineReader(Reader in) {
    super(in);
  }

  String readLine() throws IOException {
    StringBuilder strBuilder = new StringBuilder();
    int c;
    while ((c = super.read()) != -1) {
      strBuilder.append((char)c);
      if (c == '\n')
        break;
    }

    if (strBuilder.toString().equals(""))
      return null;
    return strBuilder.toString();
  }

  public static void main(String[] args) throws IOException {
    if (args.length == 0)
      System.exit(1);

    FileReader fileReader = null;
    try {
      fileReader = new FileReader(args[0]);
    } catch (FileNotFoundException e) {
      System.err.println("ファイル: " + args[0] + " が見つかりません");
      e.printStackTrace();
      System.exit(1);
    }

    LineReader lineReader = new LineReader(fileReader);
    List<String> lines = new ArrayList<String>();
    String line;
    while ((line = lineReader.readLine()) != null) {
      lines.add(line);
    }

    /* 効率は無視してバブルソートで実装 */
    for (int i = 0; i < lines.size(); ++i) {
      for (int j = i + 1; j < lines.size(); ++j) {
        if (lines.get(i).compareTo(lines.get(j)) > 0) {
          String tmp = lines.get(i);
          lines.set(i, lines.get(j));
          lines.set(j, tmp);
        }
      }
    }

    for (String l : lines) {
      System.out.println(l);
    }
  }
}
