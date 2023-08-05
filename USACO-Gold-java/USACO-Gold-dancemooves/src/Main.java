import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("dancemooves.in"));
    int N = s.nextInt(); //numcows
    int K = s.nextInt(); //numswitches
    int M = s.nextInt(); //numrounds
    int[][] switches = new int[K][2];
    for (int i = 0; i < K; i++) {
        switches[i] = new int[]{s.nextInt() - 1, s.nextInt() - 1};
    }

    HashSet[] visited = new HashSet[N];
    int[] locations = new int[N];
    for (int i = 0; i < N; i++) {
        visited[i] = new HashSet<Integer>();
        visited[i].add(i);
        locations[i] = i;
    }

    
    for (int i = 0; i < K; i++) {
        int temp = locations[switches[i][0]];
        locations[switches[i][0]] = locations[switches[i][1]];
        locations[switches[i][1]] = temp;
        visited[locations[switches[i][0]]].add(switches[i][0]);
        visited[locations[switches[i][1]]].add(switches[i][1]);
    }

    int[] results = new int[N]; //after one complete round
    for (int i = 0; i < N; i++) {
        results[locations[i]] = i;
    }
    System.out.println(Arrays.toString(results));
    /*
    for (int i = 0; i < N; i++) {
        System.out.println(visited[i]);
    }
    */
    int[] ans = new int[N]; // index (type) -> num locations
    int[] type = new int[N]; // index -> type
    for (int i = 0; i < N; i++) {
        ans[i] = -1;
        type[i] = -1;
    }
    int lastType = -1;

    for (int i = 0; i < N; i++) {

        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> totalSeen = new HashSet<>();
        int index = i;
        seen.add(i);
        while (true) {
            HashSet<Integer> check = visited[index];
            for (int a : check) {
                totalSeen.add(a);
            }
            index = results[index];

            if (type[index] != -1) {
                System.out.println(ans[type[index]] + " type 1");
                break;
            }
            if (seen.contains(index)) {
                //System.out.println(index);
                lastType += 1;
                int count = 0;
                for (int a : seen) {
                    type[a] = lastType;
                    count += 1;
                }
                ans[lastType] = count;
                System.out.println(count + " type 2");
                break;
            }
            seen.add(index);
        }
    }
    

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("dancemooves.out"))));

    }
}