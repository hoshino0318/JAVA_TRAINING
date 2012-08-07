package interpret.models;

import java.util.HashMap;

public class ObjectModel {
  private HashMap<String, Object> objects;

  public ObjectModel() {
    objects = new HashMap<String, Object>();
  }

  public void saveObject(String key, Object obj) {
    objects.put(key, obj);
  }

  public Object getObject(String key) {
    return objects.get(key);
  }

  public boolean containsObject(String key) {
    return objects.containsKey(key);
  }

  public void clearObjects() {
    objects.clear();
  }
}
