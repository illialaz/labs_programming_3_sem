package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {
      CSVReader csvRead = new CSVReader("/Users/illia/programming/labs_programming_3_sem/lab6/input.csv");
      Scanner scan = new Scanner(System.in);
      csvRead.print();

      System.out.println("Enter shortname of company you want to find");
      String shortTitle = scan.nextLine();
      List<ArrayList<String>> query1 = csvRead.find("shortTitle", shortTitle);
      csvRead.printQuery(query1);

      System.out.println("Enter a branch of company you want to find");
      String branch = scan.nextLine();
      List<ArrayList<String>> query2 = csvRead.find("branch", branch);
      csvRead.printQuery(query2);

      System.out.println("Enter kind of activity of company you want to find");
      String activity = scan.nextLine();
      List<ArrayList<String>> query3 = csvRead.find("activity", activity);
      csvRead.printQuery(query3);

      System.out.println("Enter a period of time to find out which companies were founded at that time (example: dd.mm.yyyy-dd.mm.yyyy)");
      String dateFoundation = scan.nextLine();
      List<ArrayList<String>> query4 = csvRead.find("dateFoundation", dateFoundation);
      csvRead.printQuery(query4);

      System.out.println("Enter from-to number of employees of companies you want to find (example: 120-150)");
      String employees = scan.nextLine();
      List<ArrayList<String>> query5 = csvRead.find("countEmployees", employees);
      csvRead.printQuery(query5);

      System.out.println("Enter your sql query: (Select * from table where name=\"Apple\"");
      String sqlQuery = scan.nextLine();
      List<ArrayList<String>> query6 = csvRead.find("*", sqlQuery);
      csvRead.printQuery(query6);

      csvRead.printJSON(query1, query2, query3, query4, query5, query6);
      csvRead.printXML(query1, query2, query3, query4, query5, query6);


      csvRead.close();
      scan.close();
    }
    catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
