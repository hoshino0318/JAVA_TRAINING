package ch41;

import java.util.*;

class CollectionClassifier {
  public static String classify(Set<?> s) {
    return "Set";
  }

  public static String classify(List<?> lst) {
    return "List";
  }

  public static String classify(Collection<?> c) {
    return "Unknown Collection";
  }

  public static void main(String[] args) {
    Collection<?>[] collections = {
      new HashSet<String>(),
      new ArrayList<String>(),
      new HashMap<String, String>().values(),
    };

    for (Collection<?> c : collections)
      System.out.println(classify(c));
  }
}
