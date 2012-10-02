package attr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;


class ReadAttr {
  public static Attributed readAttrs(Reader source) 
      throws IOException {
    StreamTokenizer in = new StreamTokenizer(source);
    AttributedImpl attrs = new AttributedImpl();
    Attr attr = null;
    in.commentChar('#');
    in.ordinaryChar('/');
    while (in.nextToken() != StreamTokenizer.TT_EOF) {
      if (in.ttype == StreamTokenizer.TT_WORD) {
        if (attr != null) {
         attr.setValue(in.sval);
         attr = null; // 使いきった 
        } else {
          attr = new Attr(in.sval);
          attrs.add(attr);
        }
      } else if (in.ttype == '=') {
        if (attr == null)
          throw new IOException("misplaced '='");
      } else {
        if (attr == null) // 単語が期待される
          throw new IOException("bad Attr name");
        attr.setValue(new Double(in.nval));
        attr = null;
      }
    }
    return attrs;
  }
  
  public static void main(String[] args) 
      throws IOException {
    if (args.length == 0) {
      System.err.println("needs input files");
      System.exit(1);
    }
    
    for (String fpath : args) {
      BufferedReader in = null;
      try {
        in = new BufferedReader(new FileReader(fpath));
        AttributedImpl attrs = (AttributedImpl)readAttrs(in);
        for (Attr attr : attrs) {
          System.out.println(attr);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (in != null)
          in.close();
      }
    }
  }
  
}
