import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("testsforhaybales.in"));
    //Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[] array = new int[N];
    int maxDist = 0;
    for (int i = 0; i < N; i++) {
      array[i] = s.nextInt() - 1;
      maxDist = Math.max(maxDist, array[i] - i);
    }
    //System.out.println(maxDist);

    int[] ans = new int[N];
    for (maxDist = 2; maxDist < N; maxDist++) {
      int[] minRequirement = new int[N];
      int[] maxRequirement = new int[N];
      boolean works = true;
      for (int i = 0; i < N; i++) {
        maxRequirement[i] = Integer.MAX_VALUE;
      }

      int num = 1;
      for (int i = 0; i < N; i++) {
        //System.out.println(Arrays.toString(maxRequirement) + " " + Arrays.toString(minRequirement));
        if (minRequirement[i] > maxRequirement[i]) {
          works = false;
          break;
        }
        num = Math.max(num, minRequirement[i]);
        num = Math.min(num, maxRequirement[i]);
        if (i > 0 && num < ans[i - 1]) {
          works = false;
          break;
        }
        
        ans[i] = num;
        maxRequirement[array[i]] = Math.min(maxRequirement[array[i]], ans[i] + maxDist);
        if (array[i] + 1 < N) {
          minRequirement[array[i] + 1] = Math.max(minRequirement[array[i] + 1], ans[i] + maxDist + 1);
        }
        num += 1;
      }
      if (works) {
        break;
      }
      ans = new int[N];
    }

    

    //System.out.println(Arrays.toString(ans));

    


    //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("testsforhaybales.out"))));
    System.out.println(maxDist);
    for (int i = 0; i < N; i++) {
      System.out.println(ans[i]);
    }
    //pw.close();
    
  }
}