package ch20.ex20_03;

import java.io.IOException;

class Main {
  public static void main(String[] args)
      throws IOException {
    byte code = 3;
    EncryptOutputStream out = new EncryptOutputStream(System.out, code);
    DecryptInputStream in = new DecryptInputStream(System.in, code);

    byte[] buf = {'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a'};

    out.write(buf, 0, buf.length);
    System.out.println();

    byte[] newBuf = new byte[buf.length];
    in.read(newBuf, 0, newBuf.length);

    for (int i = 0; i < newBuf.length; ++i)
      System.out.print((char)newBuf[i]);
    System.out.println();
  }
}
