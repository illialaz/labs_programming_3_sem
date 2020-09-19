package lab2;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) throws Exception {
    int n = 1;
    int m = 1;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("This program calculates sum of diagonal elements parallel to the side diagonal in matrix");
    System.out.println("Enter numbers n, m (n, m > 0) (dimension of the matrix is n * m)");

    try {
      n = scan.nextInt();
      m = scan.nextInt();
    }
    catch(Exception e) {
      System.err.println("Exception: n and m should be numbers only");
      scan.close();
      return;
    }

    if(n <= 0 || m <= 0) {
      System.err.println("Error: Numbers should be Natural (1, 2, 3,...)");
      scan.close();
      return;
    }

    Matrix matrix = new Matrix(n, m);

    int[] sumDiagonals = new int[n + m - 1];

    sumDiagonals = matrix.getSumDiagonals();

    matrix.print();

    System.out.printf("\n");

    System.out.println("Sum of diagonals");
    for(int i = 0; i < n + m - 1; i++) {
      System.out.printf("%d ", sumDiagonals[i]);
    }
    System.out.printf("\n\n");

    int minSumNum = findMinElem(sumDiagonals);

    System.out.println("Min sum: " + sumDiagonals[minSumNum]);
    scan.close();
  }

  static int findMinElem(int[] mas) {
    int res = 0;
    for(int i = 1; i < mas.length; i++) {
      if(mas[i] < mas[res]) {
        res = i;
      }
    }
    return res;
  }
}
