
import java.io.IOException;
import java.io.Writer;

class TextGenerator extends Thread {
  private Writer out;

  public TextGenerator(Writer out) {
    this.out = out;
  }

  public void run() {
    try {
      try {
        for (char c = 'a'; c <= 'z'; ++c)
          out.write(c);
      } finally {
        out.close();
      }
    } catch (IOException e) {
      getUncaughtExceptionHandler().
      uncaughtException(this, e);
    }
  }
}
