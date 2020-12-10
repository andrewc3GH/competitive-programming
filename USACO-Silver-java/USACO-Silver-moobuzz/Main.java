import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("moobuzz.in"));
    int N = s.nextInt();
    HashMap<Integer, Integer> numMap = new HashMap<>();
    numMap.put(1, 1);
    numMap.put(2, 2);
    numMap.put(3, 4);
    numMap.put(4, 7);
    numMap.put(5, 8);
    numMap.put(6, 11);
    numMap.put(7, 13);
    numMap.put(8, 14);
    numMap.put(0, 0);
    int newNum = numMap.get(N % 8);
    newNum += (15 * (int)(N/8));
    if (N % 8 == 0) {
      newNum -= 1;
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
    pw.println(newNum);
    pw.close();
  }
}