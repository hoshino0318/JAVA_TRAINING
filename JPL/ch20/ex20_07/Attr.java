package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

class Attr {
  private final String name;
  private Object value = null;
  private static final String[] types =
    {
    "Boolean", "Character", "Byte", "Short", "Integer", "Long",
    "Float", "Double", "String"
    };

  Attr(String name) {
    this.name = name;
  }

  Attr(String name, Object value) {
    this.name  = name;
    this.value = value;
  }

  Attr(String name, DataInputStream in)
      throws IOException
  {
    this.name = name;
    String type = in.readUTF();
    if (!Arrays.asList(types).contains(type))
      return;

    if (type.equals("Boolean")) {
      value = in.readBoolean();
    } else if (type.equals("Character")) {
      value = in.readChar();
    } else if (type.equals("Byte")) {
      value = in.readByte();
    } else if (type.equals("Short")) {
      value = in.readShort();
    } else if (type.equals("Integer")) {
      value = in.readInt();
    } else if (type.equals("Long")) {
      value = in.readLong();
    } else if (type.equals("Float")) {
      value = in.readFloat();
    } else if (type.equals("Double")) {
      value = in.readDouble();
    } else if (type.equals("String")) {
      value = in.readUTF();
    }
  }

  String getName() {
    return name;
  }

  Object getValue() {
    return value;
  }

  Object setValue(Object newValue) {
    Object oldVal = value;
    value = newValue;
    return oldVal;
  }

  void writeData(DataOutputStream out)
      throws IOException
  {
    Class<?> cls = value.getClass();
    String smpClassName = cls.getSimpleName();

    if (Arrays.asList(types).contains(smpClassName)) {
      out.writeUTF(smpClassName);
    } else {
      return;
    }

    if (smpClassName.equals("Boolean")) {
      out.writeBoolean((Boolean) value);
    } else if (smpClassName.equals("Character")) {
      out.writeChar((Character) value);
    } else if (smpClassName.equals("Byte")) {
      out.writeByte((Byte) value);
    } else if (smpClassName.equals("Short")) {
      out.writeShort((Short) value);
    } else if (smpClassName.equals("Integer")) {
      out.writeInt((Integer) value);
    } else if (smpClassName.equals("Long")) {
      out.writeLong((Long) value);
    } else if (smpClassName.equals("Float")) {
      out.writeFloat((Float) value);
    } else if (smpClassName.equals("Double")) {
      out.writeDouble((Double) value);
    } else if (smpClassName.equals("String")) {
      out.writeUTF((String) value);
    }
  }

  @Override
  public String toString() {
    return name + "='" + value + "'";
  }

}
