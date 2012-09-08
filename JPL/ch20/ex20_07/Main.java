package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Main {
  public static void main(String[] args)
      throws IOException
  {
    if (args.length != 1)
      throw new IllegalArgumentException("need file");

    FileOutputStream fout = new FileOutputStream(args[0]);
    DataOutputStream out = new DataOutputStream(fout);
    Attr attrOut = new Attr("attrOut", 123);
    attrOut.writeData(out);

    FileInputStream fin = new FileInputStream(args[0]);
    DataInputStream in = new DataInputStream(fin);
    Attr attrIn = new Attr("attrIn", in);
    System.out.println(attrIn.getValue());
  }
}
