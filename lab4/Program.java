package lab4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    String text;

    text = getText();

    text = parse(text);

    System.out.println(text);
  }

  static String getText() {
    String text = "";
    File input = new File("/Users/illia/programming/labs_programming_3_sem/lab4/input.in");

    try (Scanner sc = new Scanner(input, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()){
				text += sc.nextLine() + "\n";
			}
		}
		catch (IOException e) {
			e.printStackTrace();
    }
    return text;
  }

  static String parse(String text) {
    String res = "";
    boolean inString = false;
    boolean inComment = false;

    for(int i = 0; i < text.length(); i++) {
      if(!inComment && text.charAt(i) == '\"') {
        inString = !inString;

        if(text.charAt(i - 1) == '\\' && text.charAt(i - 2) == '\\') {
          inString = false;
        }

        if(text.charAt(i - 1) == '\'') {
          inString = false;
        }

        if(text.charAt(i - 1) == '\\' && text.charAt(i - 2) == '\'') {
          inString = false;
        }
      }

      if(!inString && text.charAt(i) == '/' && text.charAt(i + 1) == '*') {
        i++;
        inComment = true;
      }

      if(!inString && text.charAt(i) == '/' && text.charAt(i + 1) == '/') {
        while(text.charAt(i) != '\n') {
          i++;
        }
      }

      if(!inString && text.charAt(i) == '*' && text.charAt(i + 1) == '/') {
        i += 2;
        inComment = false;
      }

      if(!inComment) {
        res += text.charAt(i);
      }
    } 

    return res;
  }
}
