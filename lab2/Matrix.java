package lab2;

public class Matrix {
  private int n;
  private int m;
  private int[][] matrix;

  Matrix(int n, int m) {
    this.n = n;
    this.m = m;
    matrix = new int[n][m];

    final int MIN = -100;
    final int MAX = 1000;
    final int RANGE = MAX - MIN + 1;

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        matrix[i][j] = (int)(Math.random() * RANGE) + MIN;
      }
    }
  }  

  int[] getSumDiagonals() {
    int[] result = new int[this.n + this.m - 1];
    
    for(int i = 0; i < this.n; i++) {
      for(int j = 0; j < this.m; j++) {
        result[i + j] += Math.abs(this.matrix[i][j]);
      }
    }

    return result;
  }

  void print() {
    System.out.println("\nMatrix");
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        System.out.printf("%d ", matrix[i][j]);
      }
      System.out.printf("\n");
    }
  }
}