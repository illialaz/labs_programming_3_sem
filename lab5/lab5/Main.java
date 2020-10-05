package lab5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String dirName = "/Users/illia/programming/labs_programming_3_sem/lab5/lab5/";
    String fileName = "input1.html";
    HtmlFind htmlText = new HtmlFind(fileName);
  
    File input2 = new File(dirName + "input2.in");

    try {
      FileWriter output1 = new FileWriter(dirName + "output1.out");
      output1.write(htmlText.getTags());
      output1.close();
    } catch (IOException e) {
      System.out.println("An error occurred while writing to output1.out.");
      e.printStackTrace();
    }

    String linesToFind = "";

    try (Scanner sc = new Scanner(input2, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
        linesToFind += sc.nextLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
    }

    String[] masLines = linesToFind.split("[;\n]+");

    for(String line : masLines) System.out.println(line);

    try {
      FileWriter output2 = new FileWriter(dirName + "output2.out");
      FileWriter output3 = new FileWriter(dirName + "output3.out");
      for(String line : masLines) {
        output2.write(htmlText.findLine(line) + "\n");
        if(htmlText.findLine(line) == -1) output3.write(line + "\n");
      }
      output2.close();
      output3.close();
    } catch (IOException e) {
      System.out.println("An error occurred while writing to output1.out.");
      e.printStackTrace();
    }
  }
}
