import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cow.in"));
    int len = s.nextInt();
    String str = s.next();

    long C = 0;
    long O = 0;
    long W = 0;
    for (int i = 0; i < str.length(); i++) {
      if (Character.toString(str.charAt(i)).equals("C")) {
        C++;
      } else if (Character.toString(str.charAt(i)).equals("O")) {
        O += C;
      } else {
        W += O;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("cow.out"))));
    pw.println(W);
    pw.close();
  }
}