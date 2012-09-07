package ch20.ex20_09;

import java.io.File;
import java.io.IOException;
import java.util.Date;

class FileInfo {
  static void showFileInfo(String fpath) throws IOException {
    File file = new File(fpath);

    if (!file.exists()) {
      System.out.println(fpath + " not found.");
      return;
    }

    System.out.println("File name: " + file.getName());
    System.out.println("File canonical path: " + file.getCanonicalPath());

    System.out.print("File type: ");
    if (file.isDirectory())
      System.out.println("directory");
    else if (file.isFile())
      System.out.println("file");
    else
      System.out.println("special");

    int fileMode = 0;
    fileMode += file.canRead() ? 1 : 0;
    fileMode <<= 1;
    fileMode += file.canWrite() ? 1 : 0;
    fileMode <<= 1;
    fileMode += file.canExecute() ? 1 : 0;
    System.out.println("File mode: " + String.valueOf(fileMode));

    System.out.println("Last modified: " + new Date(file.lastModified()));
    System.out.println("Length: " + file.length());

    String[] fileChildren = file.list();
    if (fileChildren != null) {
      for (String fileChild : fileChildren) {
        System.out.println("Child: " + fileChild);
      }
    }
  }

  public static void main(String[] args) {
    for (String fpath : args) {
      try {
        showFileInfo(fpath);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
