import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cbarn.in"));
    int N = s.nextInt();
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt();
    }

    int minCounter = 2147483647;
    for (int start = 0; start < N; start++) {

      int[] copy = new int[N];
      for (int i = 0; i < N; i++) {
        copy[i] = array[i];
      }  
      boolean works = true;
      int counter = 0;
      ArrayList<Integer> holding = new ArrayList<>();
      boolean visitedAFullSpace = false;
      for (int i = start; i < (N * 2) + start; i++) {
        int index = i;
        if (index >= N) {
          index %= N;
        }

        //System.out.println(holding);
        if (holding.size() > 0) {
          counter += (i - holding.get(0)) * (i - holding.get(0));
          holding.remove(0);
          for (int j = 0; j < copy[index]; j++) {
            holding.add(i);
          }
          copy[index] = 1;
        } else {
          if (1 > copy[index] && visitedAFullSpace) {
            works = false;
            break;
          }
          for (int j = 1; j < copy[index]; j++) {
            holding.add(i);
          }
          copy[index] = 1;
        } 
        if (copy[index] > 0) {
          visitedAFullSpace = true;
        }
      }
      if (works) {
        minCounter = Math.min(counter, minCounter);
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    pw.println(minCounter);
    pw.close();
  }
}