import java.io.*;
import java.util.*;

class Main {  
  /*
    go through array
    add current number to fenwick tree
    query for number of values less than the current index (+ 1)
    find how many swaps need to be made across this line/index
    largest number of swaps is the answer
  */
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("sort.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }
    
    int[] sortedArray = new int[N];
    for (int i = 0; i < N; i++) {
      sortedArray[i] = array[i];
    }
    Arrays.sort(sortedArray);

    HashMap<Integer, Integer> dict = new HashMap<>();
    for (int i = 0; i < N; i++) {
      dict.put(sortedArray[i], i + 1);
    }
    

    FenwickTree ft = new FenwickTree(N);

    int maxNum = 0;
    for (int i = 0; i < N; i++) {
      ft.update(dict.get(array[i]), 1);
      maxNum = Math.max(maxNum, i + 1 - ft.query(0, i + 1));
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("sort.out"))));
    pw.println(maxNum);
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