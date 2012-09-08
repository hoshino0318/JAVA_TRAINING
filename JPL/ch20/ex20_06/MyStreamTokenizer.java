package ch20.ex20_06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.Set;
import java.util.TreeMap;
import java.text.ParseException;

class MyStreamTokenizer {
  private StreamTokenizer in;
  private SortedMap<String, Double> variables = new TreeMap<String, Double>();
  private final String[] ops = {"+", "-", "="};

  MyStreamTokenizer(Reader source) {
    in = new StreamTokenizer(source);
    in.parseNumbers();
    in.wordChars('+', '+');
    in.ordinaryChar('-');
    in.wordChars('-', '-');
    in.wordChars('=', '=');
  }

  void parse() throws IOException, ParseException {
    while (parseExpression() != StreamTokenizer.TT_EOF);
  }

  private int parseExpression() throws IOException, ParseException {
    if (in.nextToken() == StreamTokenizer.TT_EOF)
      return StreamTokenizer.TT_EOF;

    if (in.ttype != StreamTokenizer.TT_WORD) {
      throw new ParseException(in.toString(), in.lineno());
    }
    String var = in.sval;

    if (in.nextToken() != StreamTokenizer.TT_WORD) {
      throw new ParseException(in.toString(), in.lineno());
    }
    else if (!Arrays.asList(ops).contains(in.sval)) {
      throw new ParseException(in.toString(), in.lineno());
    }
    char op = in.sval.charAt(0);

    if (in.nextToken() != StreamTokenizer.TT_NUMBER)
      throw new ParseException(in.toString(), in.lineno());

    double val = in.nval;

    evaluate(var, op, val);

    return 0; // parsing success
  }

  private void evaluate(String var, char op, double val) {
    switch (op) {
    case '+':
      evalPlus(var, val);
      break;
    case '-':
      evalSubtract(var, val);
      break;
    case '=':
      evalEqual(var, val);
      break;
    default:
      break;
    }
  }

  private void evalPlus(String var, double val) {
    if (!variables.containsKey(var))
      variables.put(var, 0.0);

    double newVal = variables.get(var) + val;
    variables.put(var, newVal);
  }

  private void evalSubtract(String var, double val) {
    if (!variables.containsKey(var))
      variables.put(var, 0.0);

    double newVal = variables.get(var) - val;
    variables.put(var, newVal);
  }

  private void evalEqual(String var, double val) {
    variables.put(var, val);
  }

  void printVariables() {
    Set<SortedMap.Entry<String, Double>> vSet = variables.entrySet();
    Iterator<SortedMap.Entry<String, Double>> it = vSet.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
