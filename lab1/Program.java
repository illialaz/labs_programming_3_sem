package lab1;

import java.util.Scanner;

public class Program {
  public static void main(final String[] args) {
    double x;
    double eps;
    double result;
    final Scanner scan = new Scanner(System.in);

    System.out.println("Enter a number x and program will calculate cosh(x)");
    while(!scan.hasNextDouble()) {
      System.out.println("Enter a number");
      scan.next();
    }
    x = scan.nextDouble();

    System.out.println("Enter an integer number n (pow of little number 10^(-n)) (for example 5)");
    System.out.println("It is needed to correct the exactness of the result");
    
    while(!scan.hasNextInt()) {
      System.out.println("Enter a number");
      scan.next();
    }
    eps = Math.pow(10, -(scan.nextInt()));
    
    result = Cosh.myCosh(x, eps);

    System.out.printf("myCosh(%.2f) = %.3f\n", x, result);
    System.out.printf("cosh(%.2f)  = %.3f\n", x, Math.cosh(x));
    scan.close();
  }
}
