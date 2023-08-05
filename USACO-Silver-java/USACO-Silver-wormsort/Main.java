import java.io.*;
import java.util.*;
//search to make sure every cow is connected first

//store weight info in initial graph
//in search, check if neighbor weight is valid first
//dont remake hashmap every time
//use boolean array for visited
class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("wormsort.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[] cowArray = new int[N];
    HashMap<Integer, ArrayList<int[]>> wormholeMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int newInt = s.nextInt();
      cowArray[i] = newInt;
      wormholeMap.put(newInt, new ArrayList<>());
    }
    HashSet<Integer> wrongLocation = new HashSet<>();
    for (int i = 0; i < N; i++) {
      if (cowArray[i] != i + 1) {
        wrongLocation.add(cowArray[i]);
      }
    }
    int[][] wormholeArray = new int[M][3];
    for (int j = 0; j < M; j++) {
      int[] temp = new int[3];
      temp[0] = s.nextInt();
      temp[1] = s.nextInt();
      temp[2] = s.nextInt();
      wormholeArray[j] = temp;

      int array1[] = {temp[1], temp[2]};
      int array2[] = {temp[0], temp[2]};
      wormholeMap.get(temp[0]).add(array1);
      wormholeMap.get(temp[1]).add(array2);
    }

    int[][] sortedArray = new int[M][3];
    for (int i = 0; i < M; i++) {
      sortedArray[i] = wormholeArray[i];
    }

    Arrays.sort(sortedArray, (int[] a, int[] b) -> {
      return a[2] - b[2];
    });
    
    int repeatNum = -1;
    int currentNumber = -1;
    int minRange = 0;
    int maxRange = sortedArray[M - 1][2] + 1;
    while (true) {
      currentNumber = findIndex(minRange, maxRange);
      //HashSet<Integer> visited = new HashSet<>();
      boolean[] visitedArray = new boolean[N];
      boolean[] inQueue = new boolean[N];
      //search here
      Stack<Integer> stack = new Stack<>();
      if (sortedArray[sortedArray.length - 1][2] >= currentNumber) {
        stack.push(sortedArray[sortedArray.length - 1][0]);
      }
      //System.out.println(currentNumber + " " + wormholeMapCopy);
      //HashSet<Integer> inQueue = new HashSet<>();
      while (stack.size() > 0) {
        int currentNum = stack.pop();
        visitedArray[currentNum - 1] = true;
        for (int[] neighbor : wormholeMap.get(currentNum)) {
          if (neighbor[1] >= currentNumber && visitedArray[neighbor[0] - 1] == false && inQueue[neighbor[0] - 1] == false) {
            stack.push(neighbor[0]);
            inQueue[neighbor[0] - 1] = true;
          }
        }
      }
      boolean works = true;
      for (int item : wrongLocation) {
        if (visitedArray[item - 1] == false) {
          works = false;
          break;
        }
      }
      if (works == true) {
        minRange = currentNumber;
      } else {
        maxRange = currentNumber;
      }
      if (repeatNum == currentNumber) {
        break;
      }
      repeatNum = currentNumber;
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
    if (wrongLocation.size() == 0) {
      pw.println(-1);
    } else {
      pw.println(repeatNum);
    }
    pw.close();
  }

  public static int findIndex(int min, int max) {
    return (min + max)/2;
  }
}