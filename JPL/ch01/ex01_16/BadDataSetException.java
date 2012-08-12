package ch01.ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception {
  private static final long serialVersionUID = -683953985031682156L;

  private String setName;
  private IOException error;

  BadDataSetException(String setName, IOException error) {
    this.setName = setName;
    this.error = error;
  }

  public String getMessage() {
    return "ERROR about " + setName + " : " + error.getMessage();
  }
}
