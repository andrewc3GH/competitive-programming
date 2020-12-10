import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("convention2.in"));
    int N = s.nextInt();
    
    PriorityQueue<int[]> newQueue = new PriorityQueue<>(N, (int[] cow1, int[] cow2) -> {
      if (cow1[0] != cow2[0]) {
        return cow1[0] - cow2[0];
      } else {
        return cow1[2] - cow2[2];
      }
    });
    
    for (int i = 0; i < N; i++) {
      int[] temp = new int[3];
      temp[0] = s.nextInt();
      temp[1] = s.nextInt();
      temp[2] = i;
      newQueue.add(temp);
    }
    PriorityQueue<int[]> waitLst = new PriorityQueue<>(N, (int[] cow1, int[] cow2) -> {
      return cow1[2] - cow2[2];
    });
    int[] currentCow = new int[3];
    currentCow[0] = newQueue.peek()[0];
    currentCow[1] = newQueue.peek()[1];
    currentCow[2] = newQueue.poll()[2];
    int currentTime = 0;
    int longestTime = 0;
    waitLst.add(newQueue.poll());
    while (newQueue.size() > 0 || waitLst.size() > 0) {
      if (newQueue.size() > 0 && newQueue.peek()[0] <= currentCow[0] + currentCow[1]) {
        waitLst.add(newQueue.poll());
      } else if (waitLst.size() > 0) {
        currentTime = currentCow[0] + currentCow[1] - waitLst.peek()[0];
        if (currentTime > 0) {
          currentCow[0] = currentCow[0] + currentCow[1];
        } else {
          currentCow[0] = waitLst.peek()[0];
        }
        currentCow[1] = waitLst.peek()[1];
        currentCow[2] = waitLst.poll()[2];
        if (currentTime > longestTime) {
          longestTime = currentTime;
        }
      } else if (newQueue.size() > 0){
        waitLst.add(newQueue.poll());
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
    pw.println(longestTime);
    pw.close();
    //System.out.println(newQueue.poll());
    //System.out.println(newQueue.peek());
    //[0] = time
    //[1] = time spent
    //[2] = seniority
  }
}