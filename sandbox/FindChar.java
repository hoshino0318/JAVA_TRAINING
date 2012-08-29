import java.io.*;

class FindChar {
  public static void main(String[] args)
      throws IOException
  {
    if (args.length != 2)
      throw new IllegalArgumentException("need char and file");

    int match = args[0].charAt(0);
    FileReader fileIn = new FileReader(args[1]);
    LineNumberReader in = new LineNumberReader(fileIn);
    int ch;
    while ((ch = in.read()) != -1) {
      if (ch == match) {
        System.out.println("'" + (char)ch +
            "' at line " + in.getLineNumber());
        return;
      }
    }
    System.out.println((char)match + " not found");
  }
}
