package ch07.ex07_02;

public class NumLiteral {
  static byte numByte;
  static short numShort;
  static int numInt;
  static long numLong;
  static float numFloat;
  static double numDouble;
  
  public static void main(String args[]) {
    numByte = 1;
    numShort = 1;
    numInt = 1;
    numLong = 1;
    numFloat = 1;
    numDouble = 1;
    
    numShort = 32767;
    // numShort = 32768; 範囲を超えているのでコンパイルエラー
    numInt = 2147483647;    
    numInt = -2147483648;
    //numInt = 2147483648; 範囲を超えているのでコンパイルエラー        
  }
}
