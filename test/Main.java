import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String text = getText("input1.txt");
        String seps = getText("input2.txt") + " " + "\n";
        String resSeps = getAllSeps(text, seps);
        putText(resSeps, "output1.txt");
        String res = getWords(text, seps);
        putText(res, "output2.txt");
        String uniq = getUniqStrings(text, seps);
        putText(uniq, "output3.txt");
        String rev = reverse(text, seps);
        putText(rev, "output4.txt");
    }

    public static String getAllSeps(String text, String seps) {
        String res = "";
        for(int i = 0; i < seps.length(); i++) {
            String cur = String.valueOf(seps.charAt(i));
            if(text.contains(cur) 
            && !res.contains(cur)) {
                res += cur;
            }
        }
        char[] ch = res.toCharArray();
        Arrays.sort(ch);
        res = "";
        for(int i = ch.length - 1; i >= 0; i--) {
            res += ch[i] + "\n";
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String getWords(String text, String seps) {
        ArrayList<String> words = new ArrayList<>();
        StringTokenizer pch = new StringTokenizer(text, seps);
        while (pch.hasMoreTokens()) {
            words.add(pch.nextToken());
        }
        ArrayList<String> resArr = new ArrayList<>();
        for(String word : words) {
            boolean flag = true;
            for(String a : resArr) {
                if(a.length() == word.length()) flag = false;
            }
            if(flag && word.length() > 0) resArr.add(word);
        }
        Collections.sort(resArr, (a, b) -> { return a.length() - b.length(); });
        String res = "";
        for(String word : resArr) res += word + "\n";
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String getUniqStrings(String text, String seps) {
        ArrayList<String> words = new ArrayList<>();
        StringTokenizer pch = new StringTokenizer(text, seps);
        while (pch.hasMoreTokens()) {
            words.add(pch.nextToken());
        }
        ArrayList<String> resArr = new ArrayList<>();
        for(String word : words) {
            boolean flag = true;
            String tmp = "";
            for(char c : word.toCharArray()) {
                if(tmp.contains(String.valueOf(c))) {
                    flag = false;
                    break;
                }
                tmp += c;
            }
            if(flag) resArr.add(word);
        }
        String res = "";
        for(String word : resArr) if(!word.equals("")) res += word + "\n";
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String reverse(String text, String seps) {
        String res = "";
        String word = "";
        for(int i = 0; i < text.length(); i++) {
            if(seps.contains(String.valueOf(text.charAt(i)))) {
                StringBuilder tmp = new StringBuilder();
                res += tmp.append(word).reverse().toString() + text.charAt(i);
                word = "";
                continue;
            }
            word += text.charAt(i);
        }
        return res;
    }

    public static void putText(String text, String fileName) {
        try(FileWriter fr = new FileWriter(new File(fileName))) {
            fr.write(text);
        } catch(IOException | NullPointerException e) {
            System.out.println("Can't create file " + fileName);
        }
    }

    public static String getText(String fileName) {
        StringBuffer fileText = new StringBuffer();
        try(Scanner scan = new Scanner(new File(fileName))) {
            while (scan.hasNextLine()) {
                fileText.append(scan.nextLine() + "\n");
            }
            fileText.delete(fileText.length() - 1, fileText.length());
        } catch(FileNotFoundException e) {
            System.out.println("No such file: " + fileName);
        }
        return fileText.toString();
    }
}
