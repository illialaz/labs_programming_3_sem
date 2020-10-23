package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.lang.Integer;
import java.time.LocalDateTime;

public class CSVReader {
  private ArrayList<ArrayList<String>> table;
  private FileWriter log;

  public CSVReader(String fileName) throws Exception {
    log = new FileWriter("./lab6/logfile.txt", true);
    table = new ArrayList<ArrayList<String>>();
    try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))){
      String row;
      while ((row = csvReader.readLine()) != null) {
        table.add(new ArrayList<String>(Arrays.asList(row.split("[;]"))));
      }
      log.write(LocalDateTime.now().toString() + "\n");
      log.write("Table:\n");

      if(table.size() > 0) {
        for(ArrayList<String> line : table) {
          for(String str : line) {
            log.write(str + " ");
          }
          log.write("\n");
        }
        log.write("\n");
      }
  
      else log.write("Nothing\n");
    }
    catch(Exception e) {
      throw new Exception("Can't find file with name " + fileName + "\n");
    }
  }

  public List<ArrayList<String>> find(String rowName, String data) throws Exception {
    data = dataFromSQLQuery(data);
    if(rowName.equals("*")) {
      rowName = data.split(" ")[0];
      data = data.substring(rowName.length() + 1);
    }

    List<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    int numOfRow = table.get(0).indexOf(rowName);

    if(rowName.compareTo("dateFoundation") == 0) {
      res = findByDate(data);
    }

    else if(rowName.compareTo("countEmployees") == 0) {
      res = findByEmployees(data);
    }

    else {
      log.write("Select * from table where " + rowName + " = " + data + "\n");
      if(numOfRow < 0) throw new Exception("Can't find row with name " + "\"" + rowName + "\"");
      for(ArrayList<String> line : table) {
        if(line.get(numOfRow).compareToIgnoreCase(data) == 0) res.add(line);
      }
    }
    log.write("Num of elems: " + res.size() +"\n");
    return res;
  }

  private String dataFromSQLQuery(String data) throws Exception {
    try {
      if(data.split(" ")[0].compareToIgnoreCase("select") == 0) {
        data = data.toLowerCase();
        String tmp = new String("");
        tmp += data.split(" ")[5].split("=")[0] + " ";
        if(data.indexOf("datefoundation") != -1) {
          tmp += data.split("datefoundation>=")[1]
          .split(" and")[0];
          tmp += "-";
          tmp += data.split("datefoundation<=")[1];
        }
  
        else if(data.indexOf("countemployees") != -1) {
          tmp += data.split("countemployees>=")[1]
          .split(" and")[0];
          tmp += "-";
          tmp += data.split("countemployees<=")[1];
        }
        
        else {
          tmp += data.split("=\"")[1].split("\"")[0];
        }
        data = tmp;
      }
  
      return data;
    }
    catch(Exception e) {
      throw new Exception("Can't parse SQL query");
    }
  }

  private List<ArrayList<String>> findByDate(String data) throws Exception{
    List<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    String rowName = "dateFoundation";
    int numOfRow = table.get(0).indexOf(rowName);
    String[] mas = data.split("[-]");
    log.write("Select * from table where " + rowName + " >= " + mas[0] + " && " + rowName + " <= " + mas[1 < mas.length ? 1 : mas.length - 1] + "\n");

    String[] tmp1 = mas[0].split("[.]");
    if(tmp1.length != 3) {
      log.write("Num of elems: " + res.size() + "\n");
      return res;
    }
    Calendar date1 = new GregorianCalendar(Integer.parseInt(tmp1[2]), Integer.parseInt(tmp1[1]) - 1, Integer.parseInt(tmp1[0]));

    String[] tmp2 = mas[1].split("[.]");
    if(tmp2.length != 3) {
      log.write("Num of elems: " + res.size() + "\n");
      return res;
    }
    Calendar date2 = new GregorianCalendar(Integer.parseInt(tmp2[2]), Integer.parseInt(tmp2[1]) - 1, Integer.parseInt(tmp2[0]));
    int i = 0;
    for(ArrayList<String> line : table) {
      if(i == 0) {
        i++;
        continue;
      }
      String[] tmp3 = line.get(numOfRow).split("[.]");
      Calendar date3 = new GregorianCalendar(Integer.parseInt(tmp3[2]), Integer.parseInt(tmp3[1]) - 1, Integer.parseInt(tmp3[0]));

      if(date3.compareTo(date1) >= 0 && date3.compareTo(date2) <= 0) res.add(line);
    }
    return res;
  }

  private List<ArrayList<String>> findByEmployees(String data) throws Exception {
    List<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    String rowName = "countEmployees";
    int numOfRow = table.get(0).indexOf(rowName);
    String[] tmp = data.split("[-]");
    log.write("Select * from table where " + rowName + " >= " + tmp[0] + " && " + rowName + " <= " + tmp[1 < tmp.length ? 1 : tmp.length - 1] + "\n");
    if(tmp.length != 2) {
      log.write("Num of elems: " + res.size() + "\n");
      return res;
    }
    int[] numOfPeople = {Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])};
    int i = 0;
    for(ArrayList<String> line : table) {
      if(i == 0) {
        i++;
        continue;
      }
      if(Integer.parseInt(line.get(numOfRow)) >= numOfPeople[0] && Integer.parseInt(line.get(numOfRow)) <= numOfPeople[1]) res.add(line);
    }
    return res;
  }

  public void printJSON(List<ArrayList<String>> ...queries) {
    try (FileWriter jsonFile = new FileWriter("./lab6/queries.json", false)){
      int i = 1;
      jsonFile.write("{\n");
      for(List<ArrayList<String>> query : queries) {
        jsonFile.write("\t\"" + i++ + "\": {\n");
        int j = 1;
        for(ArrayList<String> line : query) {
            jsonFile.write("\t\t\"" + j++ + "\": [\n");
            for(String str : line) {
              if(str.compareTo(line.get(line.size() - 1)) != 0) jsonFile.write("\t\t\t\"" + str + "\",\n");
              else jsonFile.write("\t\t\t\"" + str + "\"\n");
            }
            if(line != query.get(query.size() - 1)) jsonFile.write("\t\t],\n");
            else jsonFile.write("\t\t]\n");
        }
        if(query != queries[queries.length - 1]) jsonFile.write("\t},\n");
        else jsonFile.write("\t}\n");
      }
      jsonFile.write("}\n");
    }
    catch(Exception e) {
      System.out.println("Can't open file \"queries.json\"");
    }
  }

  public void printXML(List<ArrayList<String>> ...queries) {
    try(FileWriter xmlFile = new FileWriter("./lab6/queries.xml", false)) {
      int i = 1;
      xmlFile.write("<queries>\n");
      for(List<ArrayList<String>> query : queries) {
        xmlFile.write("\t<query" + i + ">\n");
        for(ArrayList<String> line : query) {
          xmlFile.write("\t\t");
          for(String str : line) {
            xmlFile.write(str + " ");
          }
          xmlFile.write("\n");
        }
        xmlFile.write("\t</query" + (i++) + ">\n");
      }
      xmlFile.write("</queries>\n");
    }
    catch(Exception e) {
      System.out.println("Can't open file \"queries.xml\"");
    }
  }

  public void print() {
    if(table.size() > 0) {
      for(ArrayList<String> line : table) {
        for(String str : line) {
          System.out.printf("%s ", str);
        }
        System.out.println();
      }
      System.out.println();
    }

    else System.out.println("Nothing\n");
  }

  public void printQuery(List<ArrayList<String>> query) {
    if(query.size() > 0) {
      for(ArrayList<String> line : query) {
        for(String str : line) {
          System.out.printf("%s ", str);
        }
        System.out.println();
      }
      System.out.println();
    }

    else System.out.println("Nothing\n");
  }

  public void close() {
    try {
      log.write("\n\n");
      log.close();
    } catch(Exception e) {}
  }
}
