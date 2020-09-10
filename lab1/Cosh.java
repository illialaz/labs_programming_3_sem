// coshx = x + x^2/2! + x^4/4! + ...
package lab1;

class Cosh {
  public static double myCosh(final double x, final double eps) {
    double result = 1;
    int factNum = 2;
    final double DOUBLE_X = x * x;
    double next = DOUBLE_X / factNum;
    while (next > eps) {
      result += next;
      next *= (DOUBLE_X / ((factNum + 1) * (factNum + 2)));
      factNum += 2;
    }

    return result;
  }
}
