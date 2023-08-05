import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("feast.in"));
    int maxFull = s.nextInt();
    int A = s.nextInt();
    int B = s.nextInt();

    boolean[] array = new boolean[maxFull + 1];
    array[0] = true;
    for (int i = 0; i < maxFull; i++) {
      if (array[i] == true) {
        if (i + A < maxFull + 1) {
          array[i + A] = true;
        } if (i + B < maxFull + 1) {
          array[i + B] = true;
        }
      }
    }

    for (int i = 0; i < maxFull; i++) {
      if (array[i]) {
        array[i/2] = true;
      }
    }

    for (int i = 0; i < maxFull; i++) {
      if (array[i] == true) {
        if (i + A < maxFull + 1) {
          array[i + A] = true;
        } if (i + B < maxFull + 1) {
          array[i + B] = true;
        }
      }
    }
    
    int ans = 0;
    for (int i = maxFull; i > -1; i--) {
      if (array[i]) {
        ans = i;
        break;
      }
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
    pw.println(ans);
    pw.close();
  }
}