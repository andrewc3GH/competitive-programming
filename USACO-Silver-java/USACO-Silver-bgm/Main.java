import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("bgm.in"));
    int N = s.nextInt();
    HashMap<String, HashMap<Integer, Integer>> letterMap = new HashMap<>();
    HashMap<Integer, Integer> tempMap = new HashMap<>();
    for (int i = 0; i < 7; i++) {
      tempMap.put(i, 0);
    }
    letterMap.put("B", new HashMap<>(tempMap));
    letterMap.put("E", new HashMap<>(tempMap));
    letterMap.put("S", new HashMap<>(tempMap));
    letterMap.put("I", new HashMap<>(tempMap));
    letterMap.put("G", new HashMap<>(tempMap));
    letterMap.put("O", new HashMap<>(tempMap));
    letterMap.put("M", new HashMap<>(tempMap));
    for (int i = 0; i < N; i++) {
      String newLetter = s.next();
      int newNum = s.nextInt();
      HashMap<Integer, Integer> currentMap = new HashMap(letterMap.get(newLetter));
      if (newNum > -1) {
        int newCounter = currentMap.get(newNum % 7) + 1;
        currentMap.put(newNum % 7, newCounter);
      } else {
        int newCounter = 0;
        if (newNum % 7 + 7 == 7) {
          newCounter = currentMap.get(0) + 1;
          currentMap.put(0, newCounter);
        } else {
          newCounter = currentMap.get(newNum % 7 + 7) + 1;
          currentMap.put(newNum % 7 + 7, newCounter);
        }
      }
      letterMap.put(newLetter, currentMap);
    } 
    s.close();
    long counter = 0;
    for (int i = 0; i < 7; i++) {
      if (letterMap.get("B").get(i) != 0) {
        for (int j = 0; j < 7; j++) {
          if (letterMap.get("E").get(j) != 0) {
            for (int k = 0; k < 7; k++) {
              if (letterMap.get("S").get(k) != 0) {
                for (int l = 0; l < 7; l++) {
                  if (letterMap.get("I").get(l) != 0) {
                    for (int m = 0; m < 7; m++) {
                      if (letterMap.get("G").get(m) != 0) {
                        for (int n = 0; n < 7; n++) {
                          if (letterMap.get("O").get(n) != 0) {
                            for (int o = 0; o < 7; o++) {
                              if (letterMap.get("M").get(o) != 0) {
                                if (check(i, j, k, l, m, n, o)) {
                                  counter += (long)letterMap.get("B").get(i) * letterMap.get("E").get(j) * letterMap.get("S").get(k) * letterMap.get("I").get(l) * letterMap.get("G").get(m) * letterMap.get("O").get(n) * letterMap.get("M").get(o);
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));
    pw.println(counter);
    pw.close();
  }
  public static boolean check(int B, int E, int S, int I, int G, int O, int M) {
    if ((B + E + S + S + I + E)*(G + O + E + S)*(M + O + O) % 7 == 0) {
      return true;
    }
    return false;
  }
}