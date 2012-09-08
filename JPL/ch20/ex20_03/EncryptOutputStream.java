package ch20.ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class EncryptOutputStream extends FilterOutputStream {
  private final byte code;

  EncryptOutputStream(OutputStream out, byte code) {
    super(out);
    this.code = code;
  }

  @Override
  public void write(byte[] buf, int offset, int count)
      throws IOException {
    int last = offset + count;
    for (int i = offset; i < last; ++i)
      buf[i] = encrypt(buf[i]);

    out.write(buf, offset, count);
  }

  private byte encrypt(byte b) {
    byte tmp = (byte)((b + code) - 'a');
    return (byte)('a' + tmp % 26);
  }

  public static void main(String[] args) throws IOException {
    EncryptOutputStream out = new EncryptOutputStream(System.out, (byte)3);
    byte[] buf = {'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a'};

    out.write(buf, 0, buf.length);
  }
}
