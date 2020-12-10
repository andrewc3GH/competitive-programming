import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("homework.in"));
    int N = s.nextInt();
    int[] arr = new int[N];
    ArrayList<Integer> sortedArray = new ArrayList<Integer>();
    for (int i = 0; i < N; i++) {
      arr[i] = s.nextInt();
      sortedArray.add(arr[i]);
    }
    Collections.sort(sortedArray);
    double average = 0;
    int initialSum = 0;
    for (int i = 0; i < N; i++) {
      initialSum += arr[i];
    }
    average = (double)(initialSum - sortedArray.get(0))/(N - 1);
    double newAverage = 0;
    int newSum = initialSum;
    double[] maxArray = new double[N - 1];
    double maxNum = 0;
    for (int i = 0; i < (N - 2); i++) {
      newSum = newSum - arr[i];
      sortedArray.remove(new Integer(arr[i]));
      newAverage = (double)(newSum-sortedArray.get(0))/(N - i - 2);
      if (newAverage > maxNum) {
        maxNum = newAverage;
      }
      maxArray[i + 1] = newAverage;
      System.out.println(newAverage);
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
    for (int i = 0; i < N - 1; i++) {
      if (maxArray[i] == maxNum) {
        pw.println(i);
      }
    }
    pw.close();
  }
}