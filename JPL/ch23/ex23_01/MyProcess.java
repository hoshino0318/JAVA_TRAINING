package ch23.ex23_01;

import java.io.*;

class MyProcess {
  public static Process userProg(String cmd)
      throws IOException {
    Process proc = Runtime.getRuntime().exec(cmd);
    plugTogether(System.in, proc.getOutputStream());
    plugTogether(System.out, proc.getInputStream());
    plugTogether(System.err, proc.getErrorStream());
    return proc;
  }

  private static void plugTogether(final InputStream src, final OutputStream dst) {
    new Thread() {
      @Override
      public void run() {
        int ch;
        try {
          while ((ch = src.read()) != -1) {
            dst.write(ch);
          }
        } catch (IOException e) {
        }
      }
    }.start();
  }

  private static void plugTogether(final OutputStream dst, final InputStream src) {
    new Thread() {
      @Override
      public void run() {
        int ch;
        try {
          while ((ch = src.read()) != -1) {
            dst.write(ch);
          }
        } catch (IOException e) {
        }
      }
    }.start();
  }

  public static void main(String[] args)
    throws IOException {
    userProg("/bin/ls -l");
    userProg("/bin/hoge");
  }
}
