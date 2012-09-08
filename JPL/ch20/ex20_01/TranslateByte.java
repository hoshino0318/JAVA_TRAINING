package ch20.ex20_01;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

class TranslateByte {
  public static void translateByte(byte from, byte to, InputStream in, OutputStream out)
      throws IOException {
    int b;
    while ((b = in.read()) != -1)
      out.write(b == from ? to : b);
  }

  public static void main(String[] args)
      throws IOException {
    byte from = (byte) args[0].charAt(0);
    byte to = (byte) args[1].charAt(0);
    translateByte(from, to, System.in, System.out);
  }
}
