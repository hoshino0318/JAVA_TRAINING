package ch20.ex20_10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Main {
  public static void main(String[] fpaths) {
    for (String fpath : fpaths) {
      try {
        FileReader fin = new FileReader(fpath);
        WordCounter wc = new WordCounter(fin);
        wc.parse();
        wc.showWC();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
