package ch21.ex21_06;

import java.io.*;
import java.util.*;

class Concat {
  public static void main(String[] args)
      throws IOException
  {
    if (args.length == 0)
      throw new IllegalArgumentException("needs files");

    InputStream fileIn, bufIn;
    List<InputStream> inputs =
        new ArrayList<InputStream>(args.length);
    for (String arg : args) {
      fileIn = new FileInputStream(arg);
        bufIn = new BufferedInputStream(fileIn);
        inputs.add(bufIn);
    }
    Enumeration<InputStream> files =
        Collections.enumeration(inputs);

    while (files.hasMoreElements()) {
      InputStream in = files.nextElement();
      int ch;
      while ((ch = in.read()) != -1)
        System.out.write(ch);
    }
  }
}
