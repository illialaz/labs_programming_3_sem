package lab5;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

public class HtmlFind {
  private String text;
  private String tags;
  final private String dirName;

  public HtmlFind() {
    this("input.html");
  }

  public HtmlFind(String fileName) {
    this.text = "";
    this.tags = "";
    this.dirName = "/Users/illia/programming/labs_programming_3_sem/lab5/lab5/";
    File inputFile = new File(this.dirName + fileName);
    String htmlText = "";

    try (Scanner sc = new Scanner(inputFile, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
        htmlText += sc.nextLine() + "\n";
			}
		}
		catch (IOException e) {
			e.printStackTrace();
    }

    setText(htmlText);
    setTags(htmlText);
  }

  private void setText(String htmlText) {
    boolean inTag = false;
    char letter;
    StringBuffer str = new StringBuffer();
    for(int i = 0; i < htmlText.length(); i++) {
      letter = htmlText.charAt(i);
      if(!inTag && letter == '<') inTag = true;

      if(!inTag) {
        str.append(letter);
      }

      if(inTag && letter == '>') {
        inTag = false;
        letter = htmlText.charAt(i);
      }
    }

    this.text = str.toString();
  }

  private void setTags(String htmlText) {
    boolean inTag = false;
    char letter;
    StringBuffer strTag = new StringBuffer();
    for(int i = 0; i < htmlText.length(); i++) {
      letter = htmlText.toLowerCase().charAt(i);
      if(!inTag && letter == '<' && htmlText.charAt(i + 1) != '/') {
        inTag = true;
        i++;
        letter = htmlText.toLowerCase().charAt(i);
      }

      if(inTag && letter == '>') {
        inTag = false;
        strTag.append('\n');
      }

      if(inTag) {
        strTag.append(letter);
      }
    }

    String[] tmpMas = strTag.toString().split("\n");

    Comparator<String> comp = (a1, a2) -> {
      int len1 = a1.length();
      int len2 = a2.length();
      if(len1 > len2) return 1;
      if(len1 < len2) return -1;
      return a1.compareTo(a2);
    };

    Arrays.sort(tmpMas, comp);
    for(String str : tmpMas) this.tags += str + "\n";

    //Map<String, Integer> map = new HashMap<String, Integer>();

    // for(String str : tmpMas) {
    //   map.put(str, map.get(str) != null ? map.get(str) + 1 : 1);
    // }

    // while(map.size() > 0) {
    //   String minLine = (String)map.keySet().toArray()[0];
    //   for(int j = 0; j < map.size(); j++) {
    //     if(map.get(map.keySet().toArray()[j]) < map.get(minLine)) minLine = (String)map.keySet().toArray()[j]; 
    //   }
    //   this.tags += minLine + "\n";
    //   map.remove(minLine);
    // }
  }

  public String getTags() {
    return this.tags;
  }

  public String getText() {
    return this.text;
  }

  public int findLine(String line) {
    String[] mass = this.text.split("\n");

    for(int i = 0; i < mass.length; i++) {
      if(mass[i].indexOf(line) != -1) return i;
    }

    return -1;
  }
}
