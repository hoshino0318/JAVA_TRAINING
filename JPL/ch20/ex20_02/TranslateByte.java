package ch20.ex20_02;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

class TranslateByte {
  private byte from;
  private byte to;
  private InputStream  in;
  private OutputStream out;

  TranslateByte(byte from, byte to, InputStream in, OutputStream out) {
    this.from = from;
    this.to   = to;
    this.in   = in;
    this.out  = out;
  }

  byte read() throws IOException {
    int b = in.read();
    if (b == -1)
      return -1;
    return translateByte((byte) b);
  }

  int read(byte[] buf, int offset, int count)
      throws IOException {
    int nread = in.read(buf, offset, count);
    int last = offset + count;
    for (int i = offset; i < last; ++i)
      buf[i] = translateByte(buf[i]);
    return nread;
  }

  void write(byte b) throws IOException {
    out.write(translateByte(b));
  }

  void write(byte[] buf, int offset, int count)
      throws IOException {
    int last = offset + count;
    for (int i = offset; i < last; ++i)
      buf[i] = translateByte(buf[i]);
    out.write(buf, offset, count);
  }

  private byte translateByte(byte b) {
    return (b == from ? to : b);
  }

  public static void main(String[] args) throws IOException {
    TranslateByte translateByte = new TranslateByte((byte)'b', (byte)'B', System.in, System.out);
    int b;
    while ((b = translateByte.read()) != -1)
        translateByte.write((byte)b);
  }
}
