package ch01.ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

class MyUtilities {
  public double [] getDataSet(String setName)
      throws BadDataSetException
  {
    String file = setName + ".dest";
    FileInputStream in = null;
    try {
      in = new FileInputStream(file);
      return readDataSet(in);
    } catch (IOException e) {
      throw new BadDataSetException(setName, e);
    } finally {
      try {
        if (in != null)
          in.close();
      } catch (IOException e) {
        ; // 無視 : データの読み込みは成功しているか，あるいは
          // BadDataSetExceptoin をスローしようとしている
      }
    }        
  }

  /** ダミーメソッド */
  public double[] readDataSet(FileInputStream in) {
    return new double[] {0.0};
  }
}
