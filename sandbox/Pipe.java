
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

class Pipe {
  public static void main(String[] args)
      throws IOException
  {
    PipedWriter out = new PipedWriter();
    PipedReader in  = new PipedReader(out);
    TextGenerator data = new TextGenerator(out);
    data.start();
    int ch;
    while ((ch = in.read()) != -1)
      System.out.print((char) ch);
    System.out.println();
  }

}
