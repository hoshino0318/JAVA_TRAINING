package ch21.ex21_02;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

class DataHandler {
  private Map<File, byte[]> ref = new WeakHashMap<File, byte[]>(); // (おそらく) 最後のデータ

  byte[] readFile(File file) {
    byte[] data;

    // ファイルを記憶していて，データを記憶しているか調べる
    if (ref.containsKey(file)) {
      data = ref.get(file);
      if (data != null)
        return data;
    }

    // 記憶していないので，読み込む
    data = readBytesFromFile(file);
    ref.put(file, data);
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
