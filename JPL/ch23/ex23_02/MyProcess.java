package ch23.ex23_02;

import java.io.*;

class MyProcess {
  public static void exec(String[] cmd)
      throws IOException {
    Process child = Runtime.getRuntime().exec(cmd);
    InputStream childOut = child.getInputStream();
    BufferedReader in = new BufferedReader(new InputStreamReader(childOut));

    String line;
    int lineNum = 1;
    while ((line = in.readLine()) != null) {
      System.out.println(lineNum + ": " + line);
      lineNum++;
    }
  }

  public static void main(String[] cmd)
      throws IOException {
    if (cmd.length == 0) {
      System.err.println("needs args (command)");
      System.exit(1);
    }

    exec(cmd);
  }
}
