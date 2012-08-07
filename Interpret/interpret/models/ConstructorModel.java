package interpret.models;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ConstructorModel {
  private HashMap<String, Constructor<?> > constructors;

  public ConstructorModel() {
    constructors = new HashMap<String, Constructor<?> >();
  }

  public void saveConstructor(String key, Constructor<?> con) {
    constructors.put(key, con);
  }

  public Constructor<?> getConstructor(String key) {
    return constructors.get(key);
  }

  public void clearConstructors() {
    constructors.clear();
  }
}
