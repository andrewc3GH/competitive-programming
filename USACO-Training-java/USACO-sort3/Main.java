/*
ID: cruzan1
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.*;

class sort3 {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("sort3.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    int[] copyArray = Arrays.copyOf(array, N);
    Arrays.sort(copyArray);
    System.out.println(Arrays.toString(array));
    int[][] contentArray = new int[3][3];
    for (int i = 0; i < N; i++) {
      contentArray[copyArray[i] - 1][array[i] - 1] += 1;
    }
    for (int[] row : contentArray) {
      System.out.println(Arrays.toString(row));
    }
    int numDirect = 0;
    int num = 0;
    if (contentArray[0][1] != 0 && contentArray[1][0] != 0) {
      num = Math.min(contentArray[0][1], contentArray[1][0]);
      if (contentArray[0][1] == contentArray[1][0]) {
        num = contentArray[0][1];
      }
      numDirect += num;
      contentArray[0][1] -= num;
      contentArray[1][0] -= num;
    }
    if (contentArray[0][2] != 0 && contentArray[2][0] != 0) {
      num = Math.min(contentArray[0][2], contentArray[2][0]);
      if (contentArray[0][2] == contentArray[2][0]) {
        num = contentArray[0][2];
      }
      numDirect += num;
      contentArray[0][2] -= num;
      contentArray[2][0] -= num;
    }
    if (contentArray[1][2] != 0 && contentArray[2][1] != 0) {
      num = Math.min(contentArray[2][1], contentArray[1][2]);
      if (contentArray[2][1] == contentArray[1][2]) {
        num = contentArray[2][1];
      }
      numDirect += num;
      contentArray[2][1] -= num;
      contentArray[1][2] -= num;
    }
    System.out.println(numDirect);
    for (int[] row : contentArray) {
      System.out.println(Arrays.toString(row));
    }
    int numIndirect = (contentArray[0][1] + contentArray[0][2]) * 2;
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
    pw.println(numDirect + numIndirect);
    pw.close();
  }
}

//sort separate array and section off by number
//make 2d array of all the numbers in each section 
//directswaps: mirror off diagonal
//indirectswaps: top two corners * 2
//do healthy holsteins