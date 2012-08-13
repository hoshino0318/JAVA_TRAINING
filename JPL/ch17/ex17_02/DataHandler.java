package ch17.ex17_02;

import java.lang.ref.*;
import java.io.File;

class DataHandler {
  private WeakReference<File> lastFile;                  // 最後に読んだファイル
  private WeakReference<byte[]> lastData; // (おそらく) 最後のデータ

  byte[] readFile(File file) {
    byte[] data;

    // ファイルを記憶していて，データを記憶しているか調べる
    if (lastFile.get() != null & file.equals(lastFile)) {
      data = lastData.get();
      if (data != null)
        return data;
    }

    // 記憶していなので，読み込む
    data = readBytesFromFile(file);
    lastFile = new WeakReference<File>(file);
    lastData = new WeakReference<byte[]>(data);
    return data;
  }

  /* ダミー用メソッド */
  private static byte[] readBytesFromFile(File file) {
    byte[] data = new byte[100];
    for (byte i = 0; i < 100; ++i)
      data[i] = i;
    return data;
  }
}
