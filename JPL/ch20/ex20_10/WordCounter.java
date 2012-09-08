package ch20.ex20_10;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class WordCounter {
  StreamTokenizer stk;
  Map<String, Integer> wordMap;

  WordCounter(Reader soure) {
    stk = new StreamTokenizer(soure);
    wordMap = new HashMap<String, Integer>();
  }

  void parse() throws IOException {
    while (stk.nextToken() != StreamTokenizer.TT_EOF) {
      if (stk.ttype == StreamTokenizer.TT_WORD) {
        String word = stk.sval;
        Integer count = wordMap.get(word);
        if (count == null)
          wordMap.put(word, 1);
        else
          wordMap.put(word, count + 1);
      }
    }
  }

  void showWC() {
    Iterator<Map.Entry<String, Integer>> it = wordMap.entrySet().iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
