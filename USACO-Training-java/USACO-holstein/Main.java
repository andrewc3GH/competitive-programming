/*
ID: cruzan1
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

class holstein {
  public static HashSet<HashSet<Integer>> combinationList;
  public static int counter;
  public static int[] vitaminArray;
  public static int[][] feedArray;
  public static int minScoops;
  public static int minIndex;
  public static void main(String[] args) throws IOException {
    // read in input
    Scanner s = new Scanner(new File("holstein.in"));
    int V = s.nextInt();
    vitaminArray = new int[V];
    for (int i = 0; i < V; i++) {
      vitaminArray[i] = s.nextInt();
    }
    int G = s.nextInt();
    feedArray = new int[G][V];
    for (int i = 0; i < G; i++) {
      for (int j = 0; j < V; j++) {
        feedArray[i][j] = s.nextInt();
      }
    }
    findNumbers(G, V);
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
    pw.print(minScoops);
    for (int j = 0; j < G; j++) {
      if ((minIndex & (1 << j)) > 0) {
        pw.print(" " + (j + 1));
      }
    }
    pw.println();
    pw.close();
    /*
    int[] minStats = new int[V];
    HashSet<Integer> minIndex = new HashSet<Integer>();
    int minScoops = 0;
    int minFeed = 0;
    boolean first = true;
    for (HashSet<Integer> combination : combinationList) {
      int[] feedStats = new int[V];
      int numScoops = combination.size();
      int numFeed = 0;
      for (int index : combination) {
        for (int i = 0; i < V; i++) {
          feedStats[i] += feedArray[index][i];
          numFeed += feedArray[index][i];
        }
      }
      boolean works = true;
      for (int i = 0; i < V; i++) {
        if (feedStats[i] < vitaminArray[i]) {
          works = false;
          break;
        }
      }
      if (works == true) {
        if (first == true) {
          minStats = feedStats;
          minScoops = numScoops;
          minFeed = numFeed;
          minIndex = combination;
          first = false;
        } else {
          if (minScoops > numScoops) {
            minStats = feedStats;
            minScoops = numScoops;
            minFeed = numFeed;
            minIndex = combination;
          } if (minScoops == numScoops && minFeed > numFeed) {
            minStats = feedStats;
            minScoops = numScoops;
            minFeed = numFeed;
            minIndex = combination;
          }
        }
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
    pw.print(minScoops);
    for (int num : minIndex) {
      pw.print(" " + (num + 1));
    }
    pw.println();
    pw.close();
    */
  }
  public static void findCombinations(HashSet<Integer> currentArray, int G) {
    for (int i = 0; i < G; i++) {
      HashSet<Integer> copyArray = new HashSet<>(currentArray);
      counter += currentArray.size();
      /*for (int j = 0; j < currentArray.size(); j++) {
        copyArray.add(currentArray.get(j));
        counter += 1;
        if (counter % 20000 == 0) {
          System.out.println(counter);
        }
      }*/
      copyArray.add(i);
      if (currentArray.size() != copyArray.size()) {
        //Collections.sort(copyArray);
        if (!combinationList.contains(copyArray)) {
          combinationList.add(copyArray);
          findCombinations(copyArray, G);
        } 
      }
    }
    /*if (currentArray.size() > 0) {
      combinationList.add(currentArray);
    }*/
  }
  public static void findNumbers(int G, int V) {
    int num = (int)Math.pow(2, G);
    minScoops = 0;
    int minFeed = 0;
    minIndex = 0;
    boolean first = true;
    for (int i = 1; i < num; i++) {
      int[] currentNum = new int[V];
      int numScoops = 0;
      int numFeed = 0;
      for (int j = 0; j < G; j++) {
        if ((i & (1 << j)) > 0) {
          numScoops += 1;
          for (int k = 0; k < V; k++) {
            currentNum[k] += feedArray[j][k];
            numFeed += 1;
          }
        }
      }
      boolean works = true;
      for (int j = 0; j < V; j++) {
        if (currentNum[j] < vitaminArray[j]) {
          works = false;
          break;
        }
      }
      if (works == true) {
        if (first == true) {
          minScoops = numScoops;
          minFeed = numFeed;
          minIndex = i;
          first = false;
        } else if (numScoops < minScoops) {
          minScoops = numScoops;
          minFeed = numFeed;
          minIndex = i;
        } else if (numScoops == minScoops && numFeed < minFeed) {
          minScoops = numScoops;
          minFeed = numFeed;
          minIndex = i;
        }
      }
    }
  }
}