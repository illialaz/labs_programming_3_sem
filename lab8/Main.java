package lab8;

import java.io.File;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String str = getStr("input.txt");

    String str1 = deleteAllInBrackets(str);
    System.out.println(str1);
    System.out.println();

    String str2 = deleteNumbers(str);
    System.out.println(str2);
    System.out.println();

    String str3 = deleteZeroes(str);
    System.out.println(str3);
    System.out.println();
  }

  static String getStr(String fileName) {
    File text = new File("./lab8/" + fileName);
    String str = "";
    try(Scanner scan = new Scanner(text)) {
      str = scan.nextLine();
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
    }
    return str;
  }

  static String deleteAllInBrackets(String str) {
    String res = "";
    while(!res.equals(str)) {
      res = str;
      Scanner scan = new Scanner(str);
      str = "";
      scan.useDelimiter("\\([^(]*?\\)");

      while(scan.hasNext()) str += scan.next();
      scan.close();
    };

    return res;
  }

  static String deleteNumbers(String str) {
    String[] mas = str.split("\\D+");

    for(int i = 0; i < mas.length; i++) {
      if(mas[i].length() > 2) mas[i] = mas[i].substring(0, 2);
    }

    Scanner scan = new Scanner(str);
    scan.useDelimiter("[0-9]++");

    String res = "";
    int i = 0;

    while(scan.hasNext()) res += mas[i++] + scan.next();

    scan.close();
    return res;
  }

  static String deleteZeroes(String str) {
    String res = "";
    String[] mas = str.split("\\D+");

    for(int i = 0; i < mas.length; i++) {
      if(mas[i].length() > 0) {
        int a = mas[i].charAt(0) - 48;
        int length = mas[i].split("^0+").length;
        if(length > 0 && a == 0 && mas[i].length() > 1) mas[i] = mas[i].split("^0+")[1];
        if(length == 0) mas[i] = "0";
      }
    }

    Scanner scan = new Scanner(str);
    scan.useDelimiter("[0-9]++");
    int i = 0;
    while(scan.hasNext()) res += mas[i++] + scan.next();

    scan.close();
    return res;
  }
}
