import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("censor.in"));
    String longStr = s.next();
    String shortStr = s.next();
    ArrayList<String> lst = new ArrayList<>();
    for (int i = 0; i < longStr.length(); i++) {
      lst.add(Character.toString(longStr.charAt(i)));
      if (lst.size() >= shortStr.length()) {
        boolean works = true;
        for (int j = 0; j < shortStr.length(); j++) {
          if (!Character.toString(shortStr.charAt(j)).equals(lst.get(lst.size() - shortStr.length() + j))) {
            works = false;
            break;
          }
        }
        if (works) {
          for (int j = 0; j < shortStr.length(); j++) {
            lst.remove(lst.size() - 1);
          }
        }
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("censor.out"))));
    for (int i = 0; i < lst.size(); i++) {
      pw.print(lst.get(i));
    }
    pw.println();
    pw.close();
  }
}