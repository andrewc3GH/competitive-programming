import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("censor.in"));
    String longStr = s.next();
    String str = s.next();
    LinkedList<String> lst = new LinkedList<>();
    for (int i = 0; i < longStr.length(); i++) {
      lst.add(Character.toString(longStr.charAt(i)));
    }
    
    LinkedList<Integer> occurences = new LinkedList<>();
    int index = 0;
    int wordIndex = 0;
    while (index < lst.size()) {
      if (!lst.get(index).equals(Character.toString(str.charAt(wordIndex)))) {
        wordIndex = 0;
      } if (lst.get(index).equals(Character.toString(str.charAt(wordIndex)))) {
        if (wordIndex == 0) {
          occurences.add(index);
        }
        wordIndex += 1;
        if (wordIndex + 1 > str.length()) {
          for (int i = index; i > index - str.length(); i--) {
            lst.remove(i);
          }
          wordIndex = 0;
          occurences.remove(occurences.size() - 1);
          lst = check(lst, occurences, str);
        }
      }
      index += 1;
    }

    String ans = "";
    for (String a : lst) {
      ans += a;
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
    pw.println(ans);
    pw.close();
  }


  public static LinkedList check(LinkedList<String> lst, LinkedList<Integer> occurences, String str) {
    boolean works = true;
    for (int i = occurences.size() - 1; i > -1; i--) {
      int newIndex = occurences.get(i);
      for (int j = 0; j < str.length(); j++) {
        if (lst.get(newIndex + j).equals(Character.toString(str.charAt(j)))) {

        } else {
          works = false;
          break;
        }
      }
      if (works) {
        for (int k = newIndex; k < newIndex + str.length(); k++) {
          lst.remove(newIndex);
        }
        occurences.remove(i);
      } else {
        break;
      }
    }
    return lst;
  }
}

