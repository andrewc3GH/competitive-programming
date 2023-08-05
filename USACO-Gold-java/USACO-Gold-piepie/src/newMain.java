import java.io.*;
import java.util.*;

class newMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
        String line = "";
        String[] splitLine = new String[2];
        line = br.readLine();
        splitLine = line.split(" ");
        int N = Integer.parseInt(splitLine[0]);
        int D = Integer.parseInt(splitLine[1]);
        int[][] bArray = new int[N][3];
        int[][] bSorted = new int[N][3];
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            splitLine = line.split(" ");
            bArray[i] = new int[]{Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), i};
            bSorted[i] = new int[]{Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), i};
        }
        int[][] eArray = new int[N][3];
        int[][] eSorted = new int[N][3];
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            splitLine = line.split(" ");
            eArray[i] = new int[]{Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), i};
            eSorted[i] = new int[]{Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), i};
        }
        br.close();
        

        ArrayList<Integer> bZero = new ArrayList<>();
        ArrayList<Integer> eZero = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (bArray[i][1] == 0) {
                bZero.add(i);
            } if (eArray[i][0] == 0) {
                eZero.add(i);
            }
        }

        int[] bClosest = new int[N];
        int[] eClosest = new int[N];
        for (int i = 0; i < N; i++) {
            bClosest[i] = 99999999;
            eClosest[i] = 99999999;  
        }
        Arrays.sort(bSorted, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        Arrays.sort(eSorted, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        /*
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(bArray[i]));
        }
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(eArray[i]));
        }
        */

        //connections are what pie it could have come from
        //bconnections[i] is the list of j such that Bessie's pie i can be given
        //if Bessie received Elsie's jth pie. e->b
        int left = 0;
        int right = 0;
        ArrayList[] bConnections = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            bConnections[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N; i++) {
            while (right < N && eSorted[right][0] <= bSorted[i][0]) {
                right += 1; 
            }
            while (left < right && eSorted[left][0] < bSorted[i][0] - D) {
                left += 1;
            }
            //.out.println(left + " " + right);
            for (int j = left; j < right; j++) {
                bConnections[bSorted[i][2]].add(eSorted[j][2]);
            }
        }
        //System.out.println(Arrays.toString(bConnections));

        Arrays.sort(bSorted, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });
        Arrays.sort(eSorted, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });

        //dont precompute edges, only find edges when on the pie in bfs (still use pointers but with binary search)

        //econnections[i] is the list of j such that Elsie's pie i can be given
        //if Elsie received Bessie's jth pie. b->e
        left = 0;
        right = 0;
        ArrayList[] eConnections = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            eConnections[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N; i++) {
            while (right < N && bSorted[right][1] <= eSorted[i][1]) {
                right += 1; 
            }
            while (left < right && bSorted[left][1] < eSorted[i][1] - D) {
                left += 1;
            }
            //System.out.println(left + " " + right);
            for (int j = left; j < right; j++) {
                eConnections[eSorted[i][2]].add(bSorted[j][2]);
            }
        }
        //System.out.println(Arrays.toString(eConnections));
        
        
        /*
        Arrays.sort(bArray, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });
        Arrays.sort(eArray, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });
        */
        
        
        //cow in queue just recieved a gift
        //0 = b, 1 = e
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 0; i < bZero.size(); i++) {
            queue.add(bZero.get(i) + 1);
            bClosest[bZero.get(i)] = 1;
        }
        for (int i = 0; i < eZero.size(); i++) {
            queue.add(-1 * (eZero.get(i) + 1));
            eClosest[eZero.get(i)] = 1;
        }
        int queueIndex = 0;

        //System.out.println(Arrays.toString(bClosest) + Arrays.toString(eClosest));
        boolean[] evisited = new boolean[N];
        boolean[] bvisited = new boolean[N];

        while (queueIndex < queue.size()) {
            int currentIndex = queue.get(queueIndex);
            int currentCow = 0;
            if (currentIndex > 0) {
                currentCow = 0;
                currentIndex -= 1;
            } else {
                currentCow = 1;
                currentIndex *= -1;
                currentIndex -= 1;
            }
            queueIndex += 1;
            
            int otherCow = (currentCow + 1) % 2;
            if (otherCow == 1) {
                int newVal = bClosest[currentIndex] + 1;
                for (int i = 0; i < bConnections[currentIndex].size(); i++) {
                    int ind = (int)bConnections[currentIndex].get(i);
                    if (!evisited[ind] && newVal < eClosest[ind]) {
                        eClosest[ind] = newVal;
                        queue.add(-1 * (ind + 1));
                        evisited[ind] = true;
                    }
                }
            }
            if (otherCow == 0) {
                int newVal = eClosest[currentIndex] + 1;
                for (int i = 0; i < eConnections[currentIndex].size(); i++) {
                    int ind = (int)eConnections[currentIndex].get(i);
                    if (!bvisited[ind] && newVal < bClosest[ind]) {
                        bClosest[ind] = newVal;
                        queue.add(ind + 1);
                        bvisited[ind] = true;
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(bClosest) + Arrays.toString(eClosest));
        PrintStream pw = new PrintStream("piepie.out");
        //PrintWriter pw = new PrintWriter(new BufferedWriter(new PrintWriter("piepie.out")));
        for (int i = 0; i < N; i++) {
            if (bClosest[i] > N * 2) {
                pw.println(-1);
            } else {
                pw.println(bClosest[i]);
            }
        }
        pw.close();
    }
}
