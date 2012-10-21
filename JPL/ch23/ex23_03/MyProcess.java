package ch23.ex23_03;

import java.io.*;
import java.util.Arrays;

class MyProcess {
  public static void exec(String exitStr, String[] cmd)
      throws IOException {
    Process child = Runtime.getRuntime().exec(cmd);
    InputStream childOut = child.getInputStream();
    BufferedReader in = new BufferedReader(new InputStreamReader(childOut));

    String line;
    int lineNum = 1;
    while ((line = in.readLine()) != null) {
      if (line.indexOf(exitStr) != -1) {
        child.destroy();
        return;
      }
      System.out.println(lineNum + ": " + line);
      lineNum++;
    }
  }

  public static void main(String[] cmd)
      throws IOException {
    if (cmd.length < 2) {
      System.err.println("needs an exitStr and command args");
      System.exit(1);
    }

    exec(cmd[0], Arrays.copyOfRange(cmd, 1, cmd.length));
  }
}
