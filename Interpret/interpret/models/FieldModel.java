package interpret.models;

import java.lang.reflect.*;
import java.util.HashMap;

public class FieldModel {

  private HashMap<String, Field> fields;

  public FieldModel() {
    fields = new HashMap<String, Field>();
  }

  public void saveField(String fieldName, Field field) {
    fields.put(fieldName, field);
  }

  public Field getField(String fieldName) {
    return fields.get(fieldName);
  }

  public void clearFields() {
    fields.clear();
  }
}
