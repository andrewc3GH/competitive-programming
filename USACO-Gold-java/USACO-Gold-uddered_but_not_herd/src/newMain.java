import java.io.*;
import java.util.*;

class newMain {  
  public static void main(String[] args) throws IOException {
    //Scanner s = new Scanner(System.in);
    Scanner s = new Scanner(new File("uddered.in"));
    String str = s.next();

    String[] strLst = new String[str.length()];

    ArrayList<String> letters = new ArrayList<>();
    for (int i = 0; i < str.length(); i++) {
      if (!letters.contains(str.substring(i, i + 1))) {
        letters.add(str.substring(i, i + 1));
      }
      strLst[i] = str.substring(i, i + 1);
    }
    //System.out.println(letters);

    
  }
}

//some sort of interval dp

//dp with N by (letters * letters)
//letters * letters -> check if letter comes before or not