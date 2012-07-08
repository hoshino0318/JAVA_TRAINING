package ch01.ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception {
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
