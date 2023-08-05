import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    long initial = System.currentTimeMillis();

    //Scanner s = new Scanner(new File("dancemooves.in"));
    //int N = s.nextInt();
    //int K = s.nextInt();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("dancemooves.in")));
    String aline = br.readLine();
    String[] asplitLine = aline.split(" ");
    int N = Integer.parseInt(asplitLine[0]);
    int K = Integer.parseInt(asplitLine[1]);

    int[][] swapArray = new int[K][2];
    for (int i = 0; i < K; i++) {
      String line = br.readLine();
      String[] splitLine = line.split(" ");
      swapArray[i][0] = Integer.parseInt(splitLine[0]);
      swapArray[i][1] = Integer.parseInt(splitLine[1]);
    }

    int[] array = new int[N + 1];
    int[] totalNeighbors = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      array[i] = i;
    }

    for (int i = 0; i < K; i++) {
      int hold = array[swapArray[i][0]];
      array[swapArray[i][0]] = array[swapArray[i][1]];
      array[swapArray[i][1]] = hold;
    }

    //HashSet<Integer> visited = new HashSet<>(); //array of booleans instead (doesnt work)
    boolean[] visited = new boolean[N + 1];
    //HashMap<Integer, ArrayList<Integer>> groupToIndex = new HashMap<>(); 
    //HashMap<Integer, Integer> indexToGroup = new HashMap<>();
    int[] indexToGroup = new int[N + 1];
    //HashMap<Integer, HashSet<Integer>> groupToVisited = new HashMap<>();
    HashSet<Integer>[] groupToVisited = new HashSet[N + 1];
    /*
    for (int i = 0; i < N; i++) {
        groupToIndex.put(i, new ArrayList<Integer>());
        groupToVisited.put(i, new HashSet<Integer>());
    }*/
    
    int groupIndex = 0;
    for (int i = 1; i < N + 1; i++) {
        int nextIndex = i;
        HashSet<Integer> currentVisited = new HashSet<>();
        if (!visited[nextIndex]) {
          //groupToIndex.put(groupIndex, new ArrayList<Integer>());
          groupToVisited[groupIndex] = new HashSet<Integer>();
        }
        while (!visited[nextIndex]) {
            visited[nextIndex] = true;
            currentVisited.add(nextIndex);
            //groupToIndex.get(groupIndex).add(nextIndex);
            indexToGroup[nextIndex] = groupIndex;
            nextIndex = array[nextIndex];
        }
        groupIndex += 1;
    }
    
    //System.out.println(groupToIndex);
    //System.out.println(indexToGroup);

    for (int i = 1; i < N + 1; i++) {
        array[i] = i;
    }
    
    for (int i = 0; i < K; i++) {
        groupToVisited[indexToGroup[array[swapArray[i][0]]]].add(swapArray[i][1]);
        groupToVisited[indexToGroup[array[swapArray[i][1]]]].add(swapArray[i][0]);
        int hold = array[swapArray[i][0]];
        array[swapArray[i][0]] = array[swapArray[i][1]];
        array[swapArray[i][1]] = hold;
    }
    

    //System.out.println(groupToVisited);
    
    for (int i = 1; i < N + 1; i++) {
        System.out.println(Math.max(groupToVisited[indexToGroup[i]].size(), 1));
    }
    //System.out.println(System.currentTimeMillis() - initial);
  }
}