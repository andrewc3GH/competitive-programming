import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    // reading the input
    Scanner s = new Scanner(new File("cowcode.in"));
    String word = s.next();
    long index = s.nextLong();
    s.close();
    
    long currentLen = word.length();
    while (index > currentLen) {
      currentLen *= 2;
    }
  

    /*ArrayList<String> oddArray = new ArrayList<String>();
    currentWord = 0;
    for (int i = 0; i < numShifts; i++) {
      if (currentWord == word.length()) {
        currentWord = 0;
      }

      currentWord += 1;
    }*/
    System.out.println(word.length());
    while (index > (long)word.length()) {
      if ((index - currentLen/2) == 1) {
        index = currentLen/2;
      } else {
        index = index - 1 - currentLen/2;
      }
      currentLen = word.length();
      while (index > currentLen) {
        currentLen *= 2;
      }
    }
    System.out.println(index);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
    pw.println(word.charAt((int)index - 1));
    pw.close();
  }
}

/*

cow
cowwco
cowwcoocowwc
cowwcoocowwcccowwcoocoww
cowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocow
cowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcooco
cowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocoocowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcooc
cowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocoocowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocccowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocoocowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoocowwcowwcoocowwcccowwcoocowwwcowwcoocowwcccowwcoo

*/