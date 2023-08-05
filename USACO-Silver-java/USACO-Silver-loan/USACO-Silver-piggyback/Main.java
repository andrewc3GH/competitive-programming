import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(new File("piggyback.in"));
    int bEnergy = s.nextInt();
    int eEnergy = s.nextInt();
    int pEnergy = s.nextInt();
    int numFields = s.nextInt();
    int numConnections = s.nextInt();
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < numFields; i++) {
      map.put(i + 1, new ArrayList<>());
    }
    for (int i = 0; i < numConnections; i++) {
      int one = s.nextInt();
      int two = s.nextInt();
      map.get(one).add(two);
      map.get(two).add(one);
    }

    HashMap<Integer, Integer> mapFromOne = new HashMap<>();
    HashMap<Integer, Integer> mapFromTwo = new HashMap<>();HashMap<Integer, Integer> mapFromN = new HashMap<>();
    for (int startNum = 1; startNum < 4; startNum++) {
      if (startNum == 3) {
        startNum = numFields;
      }
      HashSet<Integer> visited = new HashSet<>();
      ArrayList<Integer> queue = new ArrayList<>();
      int queueIndex = 0;
      queue.add(startNum);
      visited.add(startNum);
      if (startNum == 1) {
        mapFromOne.put(startNum, 0);
      } else if (startNum == 2) {
        mapFromTwo.put(startNum, 0);
      } else if (startNum == numFields) {
        mapFromN.put(startNum, 0);
      }
      while (queueIndex < queue.size()) {
        int currentNum = queue.get(queueIndex);
        queueIndex += 1;
        for (int neighbor : map.get(currentNum)) {
          if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.add(neighbor);
            if (startNum == 1) {
              mapFromOne.put(neighbor, mapFromOne.get(currentNum) + 1);
            } else if (startNum == 2) {
              mapFromTwo.put(neighbor, mapFromTwo.get(currentNum) + 1);
            } else if (startNum == numFields) {
              mapFromN.put(neighbor, mapFromN.get(currentNum) + 1);
            }
          }
        }
      }
    }
    int withoutP = mapFromOne.get(numFields) * bEnergy + mapFromTwo.get(numFields) * eEnergy;

    for (int meetingNum = 1; meetingNum < numFields + 1; meetingNum++) {
      int cost = mapFromOne.get(meetingNum) * bEnergy + mapFromTwo.get(meetingNum) * eEnergy;
      cost += mapFromN.get(meetingNum) * pEnergy;
      withoutP = Math.min(cost, withoutP);
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
    pw.println(withoutP);
    pw.close();
  }
}