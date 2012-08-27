
import java.io.*;

class UppercaseConvertor extends FilterReader {
  public UppercaseConvertor(Reader in) {
    super(in);
  }

  @Override
  public int read() throws IOException {
    int c = super.read();
    return (c == -1 ? c : Character.toUpperCase(c));
  }

  @Override
  public int read(char[] buf, int offset, int count)
      throws IOException {
    int nread = super.read(buf, offset, count);
    int last = offset + nread;
    for (int i = offset; i < last; ++i)
      buf[i] = Character.toUpperCase(buf[i]);
    return nread;
  }

  public static void main(String[] args)
      throws IOException
      {
    StringReader src = new StringReader(args[0]);
    FilterReader f = new UppercaseConvertor(src);
    int c;
    while ((c = f.read()) != -1)
      System.out.print((char) c);
    System.out.println();
  }
}
