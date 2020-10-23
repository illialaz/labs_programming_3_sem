package lab7;

import java.util.Arrays;

public class Sort extends Thread {
  private Integer[] mas;
  private int typeOfSort;
  private int order;

  public Sort(Integer[] mas, int typeOfSort, int order) {
    super();
    this.mas = mas;
    this.typeOfSort = typeOfSort > 1 ? 1 : typeOfSort;
    this.order = order > 1 ? 1 : order;
  }

  @Override
  public void run() {
    try {
      if(typeOfSort == 0) sortByValue(order);
      if(typeOfSort == 1) sortByDigits(order);
    }
    catch(Exception e) {
      System.out.println("Bye");
    }
  }

  public Integer[] get() {
    return mas;
  }

  private void sortByValue(int order) {
    if(order == 0) Arrays.sort(mas);
    if(order == 1) mas = Arrays.stream(mas).sorted((a, b) -> b - a).toArray(Integer[]::new);
  }

  private void sortByDigits(int order) {
    if(order == 0) {
        mas = Arrays
        .stream(mas)
        .sorted((a, b) -> {
          int i = 0;
          while(a > 0) {
            i++;
            a /= 10;
          }
          int j = 0;
          while(b > 0) {
            j++;
            b /= 10;
          }
          return i - j;
        })
        .toArray(Integer[]::new);
  };

    if(order == 1) {
      mas = Arrays
      .stream(mas)
      .sorted((a, b) -> {
        int i = 0;
        while(a > 0) {
          i++;
          a /= 10;
        }
        int j = 0;
        while(b > 0) {
          j++;
          b /= 10;
        }
        return j - i;
      })
      .toArray(Integer[]::new);
    }
  }
}
