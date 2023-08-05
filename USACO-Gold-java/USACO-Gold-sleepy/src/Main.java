import java.io.*;
import java.util.*;

class Main {  
  /*
    identify sorted section of array (at end)
    put the sorted section in a fenwick tree (update by 1 at index if it exists in sorted section)
    go through unsorted section, calculate the length of the unsorted section - 1 plus the number of smaller values in the sorted section
    update the fenwick tree with each new value

    first line of output is length of the unsorted section
  */
  public static void main(String[] args) throws IOException {
    /*Scanner s = new Scanner(new File("sleepy.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }*/

    //USE BUFFERED READER

    BufferedReader br = new BufferedReader(new FileReader(new File("sleepy.in")));
    String aline = br.readLine();
    String[] asplitLine = aline.split(" ");
    int N = Integer.parseInt(asplitLine[0]);
    int[] array = new int[N];
    String aline2 = br.readLine();
    String[] asplitLine2 = aline2.split(" ");
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(asplitLine2[i]);
    }
    

    int unSorted = 0;
    int stillUnsorted = 0;

    FenwickTree ft = new FenwickTree(N);
    int currentMin = N + 1;
    for (int i = N - 1; i > -1; i--) {
      if (array[i] < currentMin) {
        currentMin = array[i];
        ft.update(array[i], 1);
      } else {
        unSorted = i + 1;
        break;
      }
    }
    stillUnsorted = unSorted;
    

    int[] ans = new int[N - 1];
    
    for (int i = 0; i < unSorted; i++) {
      ans[i] = stillUnsorted - 1 + ft.query(1, array[i]);
      ft.update(array[i], 1);
      stillUnsorted -= 1;
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("sleepy.out"))));
    pw.println(unSorted);
    for (int i = 0; i < unSorted; i++) {
      pw.print(ans[i]);
      
      if (i != unSorted - 1) {
        pw.print(" ");
      }
    }

    pw.println();
    pw.close();
  }
}

class FenwickTree {
  int length;
  int[] array;  

  public FenwickTree(int N) {
    length = N;
    array = new int[N + 1];
  }

  public FenwickTree(int N, int[] arr) {
    length = N;
    array = new int[N + 1];
    for (int i = 0; i < arr.length; i++) {
      update(i + 1, arr[i]);
    }
  }

  public void update(int index, int val) {
    while (index <= length) {
      array[index] += val;
      index += index & -index;
    }
  }

  public int sum(int index) {
    int sum = 0;
    while (index > 0) {
      sum += array[index];
      index -= index & -index;
    }
    return sum;
  }

  public int query(int startingIndex, int endingIndex) {
    int value = 0;
    value = sum(endingIndex) - sum(startingIndex - 1);
    return value;
  }
}