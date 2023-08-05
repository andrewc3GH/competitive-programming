/*
ID: cruzan1
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

class milk3 {
  public static void main(String[] args) throws IOException {
  Scanner s = new Scanner(new File("milk3.in"));
  int A = s.nextInt();
  int B = s.nextInt();
  int C = s.nextInt();

  ArrayList<Integer> start = new ArrayList<>();
  start.add(0);
  start.add(0);
  start.add(C);

  Stack<ArrayList<Integer>> stack = new Stack<>();
  stack.push(start);

  HashSet<ArrayList<Integer>> visited = new HashSet<>();

  ArrayList<Integer> cValueLst = new ArrayList<>();

  while (stack.size() > 0) {
    ArrayList<Integer> current = stack.pop();
    if (current.get(0) == 0 && !cValueLst.contains(current.get(2))) {
      cValueLst.add(current.get(2));
    } 

    visited.add(current);

    ArrayList<Integer> temp = new ArrayList<Integer>(current); //a --> b
    temp.set(1, temp.get(1) + temp.get(0));
    temp.set(0, 0);
    if (temp.get(1) > B) {
      temp.set(0, temp.get(1) - B);
      temp.set(1, B);
    }
    if (!visited.contains(temp)) {
      stack.push(temp);
    }

    ArrayList<Integer> temp1 = new ArrayList<Integer>(current); //a --> c
    temp1.set(2, temp1.get(2) + temp1.get(0));
    temp1.set(0, 0);
    if (temp1.get(2) > C) {
      temp1.set(0, temp1.get(2) - C);
      temp1.set(2, C);
    }
    if (!visited.contains(temp1)) {
      stack.push(temp1);
    }

    ArrayList<Integer> temp2 = new ArrayList<Integer>(current); //b --> a
    temp2.set(0, temp2.get(0) + temp2.get(1));
    temp2.set(1, 0);
    if (temp2.get(0) > A) {
      temp2.set(1, temp2.get(0) - A);
      temp2.set(0, A);
    }
    if (!visited.contains(temp2)) {
      stack.push(temp2);
    }

    ArrayList<Integer> temp3 = new ArrayList<Integer>(current); //b --> c
    temp3.set(2, temp3.get(2) + temp3.get(1));
    temp3.set(1, 0);
    if (temp3.get(2) > C) {
      temp3.set(1, temp3.get(2) - C);
      temp3.set(2, C);
    }
    if (!visited.contains(temp3)) {
      stack.push(temp3);
    }

    ArrayList<Integer> temp4 = new ArrayList<Integer>(current); //c --> a
    temp4.set(0, temp4.get(0) + temp4.get(2));
    temp4.set(2, 0);
    if (temp4.get(0) > A) {
      temp4.set(2, temp4.get(0) - A);
      temp4.set(0, A);
    }
    if (!visited.contains(temp4)) {
      stack.push(temp4);
    }

    ArrayList<Integer> temp5 = new ArrayList<Integer>(current); //c --> b
    temp5.set(1, temp5.get(1) + temp5.get(2));
    temp5.set(2, 0);
    if (temp5.get(1) > B) {
      temp5.set(2, temp5.get(1) - B);
      temp5.set(1, B);
    }
    if (!visited.contains(temp5)) {
      stack.push(temp5);
    }
  }
  Collections.sort(cValueLst);
  int lastElement = cValueLst.get(cValueLst.size() - 1);
  PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
  for (int i : cValueLst) {
    if (i != lastElement) {
      pw.print(i + " ");
    } else {
      pw.print(i + "\n");
    }
  }
  pw.close();
  //DFS: at each stage, you can pour into bucket A, B, or C from each different bucket
  //find all of these combinations and put the ones with A = 0 into an arraylist (their C value)
  //output the sorted arraylist 
  }
}