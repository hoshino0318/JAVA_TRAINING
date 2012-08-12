package ch09.ex09_03;

class PascalsTriangle {
  private final char[][] triangle;  // トライアングルを char で表現

  public PascalsTriangle(int depth) {
    this.triangle = new char[depth][];
    for (int i = 0; i < triangle.length; ++i) {
      triangle[i] = new char[i+1];
      for (int j = 0; j < triangle[i].length; ++j) {
        triangle[i][j] = '*';
      }
    }
  }

  public void show() {
    for (int i = 0; i < triangle.length; ++i) {
      for (int j = 0; j < triangle[i].length; ++j) {
        System.out.print(triangle[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    PascalsTriangle pascalsTriangle = new PascalsTriangle(12);

    pascalsTriangle.show();
  }
}
