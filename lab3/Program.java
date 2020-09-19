package lab3;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    String text;

    text = getText();

    String[] words = text.split("[ .,]");

    Arrays.sort(words, (a, b) -> a.toLowerCase().compareTo(b.toLowerCase()));

    for(String str : words) {
      System.out.println(str);
    }
  }

  static String getText() {
    Scanner scan = new Scanner(System.in);
    String text = "";
    String nextLine = scan.nextLine();
    while(nextLine.compareTo("") != 0) {
      text += nextLine + " ";
      nextLine = scan.nextLine();
    }
    scan.close();
    return text;
  }
}
