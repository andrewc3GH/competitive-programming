import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("convention.in"));
    int N = s.nextInt();
    int numbuses = s.nextInt();
    int busCapacity = s.nextInt();
    int[] cowArray = new int[N];
    for (int i = 0; i < N; i++) { 
      cowArray[i] = s.nextInt();
    }
    Arrays.sort(cowArray, 0, cowArray.length);
    int upperRange = cowArray[cowArray.length - 1] - cowArray[0];
    int lowerRange = 0;
    int middle = (upperRange + lowerRange)/2;
    int oldNum = -1;
    while (lowerRange <= upperRange) {
      middle = (upperRange + lowerRange)/2;
      if ((upperRange + lowerRange) % 2 != 0) {
        middle += 1;
      }
      boolean works = true;
      int firstCow = cowArray[0];
      int spaceTaken = 1;
      int busesTaken = 1;
      works = true;
      for (int i = 1; i < N; i++) {
        if (busesTaken > numbuses) {
          works = false;
          break;
        }
        spaceTaken += 1;
        if (cowArray[i] - firstCow <= middle && spaceTaken <= busCapacity) {
          continue;
        } else {
          spaceTaken = 1;
          firstCow = cowArray[i];
          busesTaken += 1;
        }
      }
      if (busesTaken > numbuses) {
        works = false;
      }
      if (works == true) {
        upperRange = middle;
      } else {
        lowerRange = middle;
      }
      if (oldNum == middle) {
        break;
      }
      oldNum = middle;
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
    pw.println(middle);
    pw.close();
  }
}