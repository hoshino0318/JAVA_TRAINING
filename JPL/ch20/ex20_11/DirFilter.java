package ch20.ex20_11;

import java.io.File;
import java.io.FilenameFilter;

class DirFilter implements FilenameFilter {
  private final String suffix;

  DirFilter(String suffix) {
    this.suffix = suffix;
  }

  @Override
  public boolean accept(File dir, String name) {
    return name.endsWith(suffix);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("need dirpath and suffix");
    }

    File dir = new File(args[0]);
    if (!dir.exists()) {
      System.out.println(dir.getName() + " not found.");
      System.exit(1);
    }

    String[] files = dir.list(new DirFilter(args[1]));
    System.out.println(files.length + " dir(s):");
    for (String file : files)
      System.out.println("\t" + file);
  }
}
