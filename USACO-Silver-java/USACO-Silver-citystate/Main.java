import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("citystate.in"));
    int N = s.nextInt();
    HashMap<String,Integer> instancesMap = new HashMap<>();
    String[][] array = new String[N][2]; 
    for (int i = 0; i < N; i++) {
      String firstWord = s.next();
      String secondWord = s.next();
      firstWord = firstWord.substring(0, 2);
      String currentString = firstWord + secondWord;
      if (!firstWord.equals(secondWord)){
        if (instancesMap.containsKey(currentString)) {
          instancesMap.replace(currentString, instancesMap.get(currentString) + 1);
        } else {
          instancesMap.put(currentString, 1);
        }
      }
    }

    System.out.println(instancesMap);
    int counter = 0;
    for (String key: instancesMap.keySet()) {
      String newKey = key.substring(2, 4) + key.substring(0, 2);
      if (instancesMap.containsKey(newKey)) {
        counter += instancesMap.get(newKey) * instancesMap.get(key);
        /*if (reversedArray[i].substring(0, 2).equals(reversedArray[i].substring(2, 4))) {
          counter -= 1;
        }*/
      }
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
    pw.println(counter/2);
    pw.close();
    /*
    int counter = 0;
    for (int i = 0; i < N; i++) {
      String[] newArray = new String[2];
      newArray[0] = array[i][1];
      newArray[1] = array[i][0];
      String weirdString = array[i][1]+array[i][0];
      int oldLen = copyArray.size();
      copyArray.removeAll(Collections.singleton(weirdString));
      int newCounterThingy = copyArray.size();
      int thisisrealCounter = 1;
      for (int j = 1; j < oldLen - newCounterThingy; j++) {
        thisisrealCounter *= j;
      }
      counter += thisisrealCounter;
    }
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
    pw.println(counter/2);
    pw.close();*/
  }
}

//construct hash map mapping string to number of occurences
//go through array again and multiply if there is a match
//divide all matches by 2