package lab7;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Integer[] mas = new Integer[10];
    Random rand = new Random();
    for(int i = 0; i < mas.length; i++) mas[i] = rand.nextInt(150);


    Sort a = new Sort(mas, 0, 1);
    a.start();
    try {
      a.join();
    }
    catch(Exception e) {}

    mas = a.get();

    for(int elem : mas) System.out.printf("%d ", elem);
    System.out.println();

    System.out.println("Bye from main");
  }
}
