/*
ID: cruzan1
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.*;

class ariprog {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("ariprog.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    HashSet<Integer> trueSet = new HashSet<>();
    boolean[] biSquareArray = new boolean[2*M*M + 1];
    for (int i = 0; i < M + 1; i++) {
      for (int j = 0; j < M + 1; j++) {
        biSquareArray[i*i + j*j] = true;
        trueSet.add(i*i + j*j);
      }
    }
    System.out.println(trueSet);
    ArrayList<int[]> sortedLst = new ArrayList<>(); 

    for (int i : trueSet) {
      for (int j = 1; j <= (biSquareArray.length - i)/(N - 1); j++) {
        boolean add = true;
        for (int k = 1; k < N; k++) {
          if (!trueSet.contains(i + (j * k))) {
            add = false;
            break;
          }
        }
        if (add == true) {
          int[] temp = {j, i};
          sortedLst.add(temp);
        }
      }
    }
    Collections.sort(sortedLst, new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        if (a[0] > b[0]) {
          return 1;
        } else if (a[0] < b[0]) {
          return -1;
        } else if (a[1] > b[1]) {
          return 1;
        } else if (a[1] < b[1]) {
          return -1;
        }
        return 0;
      }
    });
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    for (int[] a : sortedLst) {
      pw.println(a[1] + " " + a[0]);
    }
    if (sortedLst.size() == 0) {
      pw.println("NONE");
    }
    pw.close();
    //for each loop through trueSet (a values)
    //find all b values starting at an a value that it can go up by (max is (end - startValue)/(N-1))
    //keep track of how many sequences work

    //sort: put each sequence in array --> [b, a]
    //put arrays in an arraylist and sort it
    //output: go through each element in arraylist and reverse the elements [b, a] --> [a, b]
  }
}