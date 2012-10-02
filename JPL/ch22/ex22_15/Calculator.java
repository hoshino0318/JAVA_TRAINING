package ch22.ex22_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Calculator {
  private static Deque<Double> stack;
  private static final Class<Math> math = Math.class;
  private static List<Method> methods;

  //TODO : 引数が int のメソッドも呼べるように
  static {
    methods = new ArrayList<Method>();
    Method[] candidateMethods = math.getDeclaredMethods();
    for (Method method : candidateMethods) {
      Class<?> retClass = method.getReturnType();
      List<Class<?>> clss = Arrays.asList(new Class<?>[]{double.class, int.class});
      if ((clss.indexOf(retClass) != -1)
          && (isParamDouble(method)) || isParamEmpty(method)) {
        methods.add(method);
      }
    }
  }

  private static boolean isParamDouble(Method method) {
    Class<?>[] clss = method.getParameterTypes();
    for (Class<?> cls : clss) {
      if (cls != double.class)
        return false;
    }
    return true;
  }

  private static boolean isParamEmpty(Method method) {
    return method.getParameterTypes().length == 0;
  }

  private static Method getAppropriateMethod(String methodName) {
    for (Method method : methods) {
      if (method.getName().equals(methodName))
        return method;
    }
    return null;
  }

  private static boolean isBacicOperator(String token) {
    String[] ops = {"+", "-", "*", "/", "%"};
    for (String op : ops) {
      if (op.equals(token))
        return true;
    }
    return false;
  }

  private static double calcBacicOperation(String op)
      throws IOException {
    if (stack.size() < 2)
      throw new IOException("不正な入力です");

    double op1, op2;
    op2 = stack.pollLast();
    op1 = stack.pollLast();

    switch (op) {
    case "+":
      return op1 + op2;
    case "-":
      return op1 - op2;
    case "*":
      return op1 * op2;
    case "/":
      return op1 / op2;
    case "%":
      return op1 % op2;
    default:
      throw new AssertionError(op);
    }
  }

  private static double calcLine(String line)
       throws NoSuchMethodException, IOException {
    stack = new ArrayDeque<Double>();
    StringTokenizer tokens = new StringTokenizer(line, " ");

    while (tokens.hasMoreTokens()) {
      String token = tokens.nextToken();
      Double val = null;
      try {
        val = Double.parseDouble(token);
        stack.add(val);
      } catch (NumberFormatException e) {
      }
      if (val != null)  // 数値のときは次へ
        continue;

      if (isBacicOperator(token)) {
        try {
          stack.add(calcBacicOperation(token));
        } catch (IOException e) {
          throw new IOException("不正な入力です: " + line);
        }
      } else {
        Method method = getAppropriateMethod(token);
        if (method == null) {
          throw new NoSuchMethodException("関数 " + token + " が見つかりません");
        }

        int paramNum = method.getParameterTypes().length;
        if (stack.size() < paramNum) {
          throw new IOException("不正な入力です: " + line);
        }

        Object[] params = new Object[paramNum];
        for (int i = 0; i < paramNum; ++i) {
          params[paramNum-i-1] = stack.pollLast();
        }

        try {
          double d = (double)method.invoke(null, params);
          stack.add(d);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }

    if (stack.size() != 1)  // スタックに余計なトークンが残っている場合はエラー
      throw new IOException("不正な入力です: " + line);

    return stack.pollLast();
  }

  public static void main(String[] args)
      throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String line = null;
    System.out.print("> ");
    while ((line = in.readLine()) != null) {
      try {
        System.out.println(calcLine(line));
      } catch (NoSuchMethodException e) {
        System.out.println(e.getMessage());
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      System.out.print("> ");
    }
  }
}
