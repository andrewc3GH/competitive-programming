import java.io.*;
import java.util.*;

//use int[] instead of hashset -> each index represents a different barn
//use recursive version
//run DFS from every unvisited location (for loop)

class Main {
  public static int[] componentArray;
  public static int componentIndex = 1;
  public static String cows;
  public static HashMap<Integer, ArrayList<Integer>> connectionMap; 
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("milkvisits.in"));
    int N = s.nextInt();
    int M = s.nextInt();
    connectionMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      connectionMap.put(i + 1, new ArrayList<Integer>());
    }

    componentArray = new int[N + 1];

    cows = s.next();

    for (int i = 0; i < N - 1; i++) {
      int first = s.nextInt();
      int second = s.nextInt();
      connectionMap.get(first).add(second);
      connectionMap.get(second).add(first);
    }

    for (int i = 1; i < N + 1; i++) {
      componentIndex = i;
      DFS(i);
    }

    //System.out.println(connectionMap);
    /*
    HashMap<Integer, Integer> ComponentMap = new HashMap<>();
    HashMap<Integer, String> LetterMap = new HashMap<>();

    
    //System.out.println(connectionMap);
    Stack otherStack = new Stack();
    Stack currentStack = new Stack();
    currentStack.push(1);
    String currentLetter = String.valueOf(cows.charAt(0));
    HashSet<Integer> visited = new HashSet<>();
    ArrayList<Integer> currentLst = new ArrayList<>();
    int compNum = 1;
    while (true) {
      System.out.println(currentStack + " " + otherStack + " " + currentLst);
      int currentNum = 0;
      if (currentStack.size() > 0) {
        currentNum = (int)currentStack.pop();
        currentLst.add(currentNum);
      } else {
        for (int item : currentLst) {
          ComponentMap.put(item, compNum);
        }
        if (currentLetter.equals("G")) {
          LetterMap.put(compNum, "G");
        } else {
          LetterMap.put(compNum, "H");
        }
        compNum += 1;
        currentNum = (int)otherStack.pop();
        currentLst = new ArrayList<>();
        currentLetter = String.valueOf(cows.charAt(currentNum - 1));
        while (otherStack.size() > 0) {
          currentStack.push(otherStack.pop());
        }
        currentLst.add(currentNum);
      }
      visited.add(currentNum);
      
      for (int neighbor : connectionMap.get(currentNum)) {
        if (!visited.contains(neighbor)) {
          if (String.valueOf(cows.charAt(neighbor - 1)).equals(currentLetter)) {
            currentStack.push(neighbor);
          } else {
            otherStack.push(neighbor);
          }
        }
      }
      if (otherStack.size() == 0 && currentStack.size() == 0) {
        if (currentLetter.equals("G")) {
        LetterMap.put(compNum, "G");
        } else {
          LetterMap.put(compNum, "H");
        }
        for (int item : currentLst) {
          if (!ComponentMap.containsKey(item)) {
            ComponentMap.put(item, compNum);
          }
        }
        break;
      }
    }*/

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

    for (int i = 0; i < M; i++) {
      int start = s.nextInt();
      int finish = s.nextInt();
      String currentString = s.next();
      // different components
      if (componentArray[start] != componentArray[finish]) {
        pw.print(1);
      } else if (currentString.equals(String.valueOf(cows.charAt(start - 1)))) {
        pw.print(1);
      } else {
        pw.print(0);
      }
    }
    pw.println();
    pw.close();
  }
  public static void DFS(int barn) {
    if (componentArray[barn] != 0) {
      return;
    }
    componentArray[barn] = componentIndex;
    for (int neighbor : connectionMap.get(barn)) {
      if (cows.charAt(neighbor - 1) == (cows.charAt(barn - 1))) {
        DFS(neighbor);
      }
    }
  }

}

/*
1H - 5G
|   
2H - 3G
|
4H
*/

//dfs and give designate which component each farm is in (array -> index corresponds to farm number)
//for every farm pair, make sure the two farms arent in the same component
//kattis