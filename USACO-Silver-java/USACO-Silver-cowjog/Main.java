import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("cowjog.in"));
    int N = s.nextInt();
    int T = s.nextInt();

    //position, speed
    ArrayList<long[]> array = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      array.add(new long[]{s.nextLong(), s.nextLong()});
    }
    Collections.reverse(array);
    
    long[] endPos = new long[N];
    for (int i = 0; i < N; i++) {
      endPos[i] = array.get(i)[0] + array.get(i)[1] * T;
    }
    
    long altAns = 1;
    long currentMax = endPos[0];
    for (int i = 0; i < N; i++) {
      if (currentMax > endPos[i]) {
        currentMax = endPos[i];
        altAns += 1;
      } else {
        endPos[i] = currentMax;
      }
    }

    long counter = 0;
    HashSet<Long> nums = new HashSet<>();
    for (int i = 0; i < N; i++) {
      if (!nums.contains(endPos[i])) {
        nums.add(endPos[i]);
        counter += 1;
      }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
    pw.println(counter);
    pw.close();
    //System.out.println(counter + " = " + altAns);
  }
}