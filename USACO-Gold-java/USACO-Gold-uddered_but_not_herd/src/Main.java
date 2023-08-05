import java.io.*;
import java.util.*;

class Main {  
  public static void main(String[] args) throws IOException {
    //Scanner s = new Scanner(System.in);
    Scanner s = new Scanner(new File("uddered.in"));
    String str = s.next();

    String[] strLst = new String[str.length()];

    ArrayList<String> letters = new ArrayList<>();
    for (int i = 0; i < str.length(); i++) {
      if (!letters.contains(str.substring(i, i + 1))) {
        letters.add(str.substring(i, i + 1));
      }
      strLst[i] = str.substring(i, i + 1);
    }
    //System.out.println(letters);

    int maxVal = 0;

    for (int i = 0; i < letters.size(); i++) {
      for (int j = i + 1; j < letters.size(); j++) {
        int currentMin = Integer.MAX_VALUE;
        String firstLetter = letters.get(i);
        String secondLetter = letters.get(j);
        int numIterations = 0;
        String lastLetter = null;
        for (int k = 0; k < str.length(); k++) {
          if (strLst[k].equals(firstLetter) || strLst[k].equals(secondLetter)) {
            if (strLst[k].equals(lastLetter)) {
              numIterations += 1;
            } else if (strLst[k].equals(secondLetter) && firstLetter.equals(lastLetter)) {

            } else {
              numIterations += 1;
            }
            lastLetter = strLst[k];
          }
        }
        currentMin = Math.min(currentMin, numIterations);
        firstLetter = letters.get(j);
        secondLetter = letters.get(i);
        numIterations = 0;
        lastLetter = null;
        for (int k = 0; k < str.length(); k++) {
          if (strLst[k].equals(firstLetter) || strLst[k].equals(secondLetter)) {
            if (strLst[k].equals(lastLetter)) {
              numIterations += 1;
            } else if (strLst[k].equals(secondLetter) && firstLetter.equals(lastLetter)) {

            } else {
              numIterations += 1;
            }
            lastLetter = strLst[k];
          }
        }
        currentMin = Math.min(currentMin, numIterations);
        //System.out.println(firstLetter + " " + secondLetter + " " + numIterations);
        maxVal = Math.max(maxVal, currentMin);
      }
    }
    System.out.println(maxVal);
    
  }
}

//some sort of interval dp