/*
ID: cruzan1
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.*;

class numtri {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
    int R = Integer.parseInt(br.readLine());
    ArrayList<int[]> triangleArray = new ArrayList<int[]>();
    //long startTime = System.nanoTime();
    //int counter = 0;
    for (int i = 1; i < R + 1; i++) {
      String[] tempArray = br.readLine().split(" ");
      int[] temporaryArray = new int[i];
      for (int j = 0; j < i; j++) {
        temporaryArray[j] = Integer.parseInt(tempArray[j]);
      }
      triangleArray.add(temporaryArray);
      /*long endTime = System.nanoTime();
      System.out.println("Took "+(endTime - startTime) + " ns"); */
    }
    //System.out.println(counter);
    Collections.reverse(triangleArray);
    for (int k = 0; k < R; k++) {
      System.out.println(Arrays.toString(triangleArray.get(k)));
    }
    for (int i = 0; i < R - 1; i++) {
      for (int j = 0; j < R - i - 1; j++) {
        int newNum = triangleArray.get(i + 1)[j];
        if (triangleArray.get(i)[j] < triangleArray.get(i)[j + 1]) {
          newNum += triangleArray.get(i)[j + 1];
        } else {
          newNum += triangleArray.get(i)[j];
        }
        triangleArray.get(i + 1)[j] = newNum;
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
    pw.println(triangleArray.get(R - 1)[0]);
    pw.close();
  }
}