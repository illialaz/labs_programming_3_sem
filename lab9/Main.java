package lab9;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    ArrayList<String> urls = findUrls("input.html");

    writeDataFromUrl("output1.txt", "output2.txt", urls);
  }

  static ArrayList<String> findUrls(String fileName) {
    File htmlText = new File("./lab9/" + fileName);
    ArrayList<String> urls = new ArrayList<String>();
    try(Scanner scan = new Scanner(htmlText)){
      while(scan.hasNextLine()) {
        String tmp = scan.nextLine();
        if(tmp.matches("(.*)href=(.*)")) urls.add(tmp.split("href=\"")[1].split("\"")[0] + " ");
      }
    }
    catch(Exception e) {
      System.out.println("Can't find file \"input.html\"");
    }
    return urls;
  }

  static void writeDataFromUrl(String fileName1, String fileName2, ArrayList<String> urls) {
    try(FileWriter output = new FileWriter("./lab9/output1.txt");
          FileWriter err = new FileWriter("./lab9/output2.txt")) {

      for(String url : urls) {
        try{
          URLConnection connection = null;

          connection =  new URL(url).openConnection();

          Map<String, List<String>> map = connection.getHeaderFields();
          output.write(url + "\n");
          for (String key : map.keySet()) {
            output.write(key + ":" + "\n");
            List<String> values = map.get(key);
            for (String aValue : values) {
              output.write("\t" + aValue);
            }
            output.write("\n");
          }
          output.write("\n");
        }
        catch(Exception e) {
          err.write(url + "\n\n");
        }
      }
    } catch(Exception e) {
      System.out.println("Can't open files \"output1.txt\" and \"output2.txt\"");
    }
  }
}
