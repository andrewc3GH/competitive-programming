import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("revegetate.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    int[][] array = new int[M][3];
    String[] letters = new String[M];
    for (int i = 0; i < M; i++) {
      letters[i] = s.next();
      array[i][0] = s.nextInt();
      array[i][1] = s.nextInt();
      array[i][2] = i;
    }

    HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
    for (int i = 1; i < N + 1; i++) {
      connections.put(i, new ArrayList<Integer>());
    }

    ArrayList<HashMap<Integer, String>> valueArray = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      valueArray.add(new HashMap<Integer, String>());
    }

    for (int[] item : array) {
      valueArray.get(item[0] - 1).put(item[1] - 1, letters[item[2]]);
      valueArray.get(item[1] - 1).put(item[0] - 1, letters[item[2]]);
      connections.get(item[1]).add(item[0]);
      connections.get(item[0]).add(item[1]);
    }

    //System.out.println(connections);
    /*
    int numComponents = 0;
    HashSet<Integer> visited = new HashSet<>();
    for (int i = 1; i < N + 1; i++) {
      ArrayList<Integer> stack = new ArrayList<>();
      stack.add(i);
      if (!visited.contains(i)) {
        numComponents += 1;
        while (stack.size() > 0) {
          int currentNum = stack.get(stack.size() - 1);
          stack.remove(stack.size() - 1);
          visited.add(currentNum);
          for (int neighbor : connections.get(currentNum)) {
            if (!visited.contains(neighbor)) {
              stack.add(neighbor);
            }
          }
        }
      } 
    }
    */
    System.out.println(connections);
    System.out.println(valueArray);
    boolean returnZero = false;
    int[] worksArray = new int[N];
    HashSet<Integer> newVisited = new HashSet<>();
    for (int i = 0; i < N; i++) {
      if (worksArray[i] == 0) {
        ArrayList<Integer> stack = new ArrayList<>();
        stack.add(i);
        int number = 1;
        while (stack.size() > 0) {
          int currentNum = stack.get(stack.size() - 1);
          stack.remove(stack.size() - 1);
          worksArray[currentNum] = number;
          for (int neighbor : valueArray.get(currentNum).keySet()) {
            String sameODifferent = "";
            if (!newVisited.contains(neighbor)) {
              stack.add(neighbor);
              newVisited.add(neighbor);
              sameODifferent = valueArray.get(currentNum).get(neighbor);
              if (!sameODifferent.equals("S")) {
                if (number == 2) {
                  number = 1;
                } else {
                  number += 1;
                } //instead of number, use the last used number in works array (Adjacent)
              }
              if (worksArray[neighbor - 1] != 0 && worksArray[neighbor - 1] != number) {
                System.out.println(Arrays.toString(worksArray));
                returnZero = true;
                break;
              }
              System.out.println(sameODifferent);
              if (sameODifferent.equals("S")) {
                worksArray[neighbor - 1] = number;
              } else {
                if (number == 2) {
                  number = 1;
                } else {
                  number += 1;
                }
                worksArray[neighbor - 1] = number;
              }
            }
          }
        }
      }
    }
    System.out.println(returnZero);
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
    pw.print(1);
    for (int i = 0; i < numComponents; i += 1) {
      pw.print(0);
    }
    pw.close();
  }
}