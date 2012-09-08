package ch20.ex20_03;

import java.io.InputStream;
import java.io.IOException;
import java.io.FilterInputStream;

class DecryptInputStream extends FilterInputStream {
  private final byte code;

  DecryptInputStream(InputStream in, byte code) {
    super(in);
    this.code = code;
  }

  @Override
  public int read() throws IOException {
    byte b = (byte)super.read();
    return decrypt(b);
  }

  @Override
  public int read(byte[] buf, int offset, int count) throws IOException {
    int nread = super.read(buf, offset, count);
    int last = offset + count;
    for (int i = offset; i < last; ++i)
      buf[i] = decrypt(buf[i]);

    return nread;
  }

  private byte decrypt(byte b) {
    byte tmp = (byte)(b - 'a' - code + 26);
    return (byte)('a' + tmp % 26);
  }

  public static void main(String[] args) throws IOException {
    DecryptInputStream in = new DecryptInputStream(System.in, (byte)3);

    byte b;
    while ((b = (byte)in.read()) != -1)
      System.out.print((char)b);
  }
}
