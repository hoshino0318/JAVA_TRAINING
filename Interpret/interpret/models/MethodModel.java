package interpret.models;

import java.lang.reflect.*;
import java.util.HashMap;

public class MethodModel {

  private HashMap<String, Method> methods;

  public MethodModel() {
    methods = new HashMap<String, Method>();
  }

  public void saveMethod(String methodName, Method method) {
    methods.put(methodName, method);
  }

  public Method getMethod(String methodName) {
    return methods.get(methodName);
  }

  public void clearMethods() {
    methods.clear();
  }
}
