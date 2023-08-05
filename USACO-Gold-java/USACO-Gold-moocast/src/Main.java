import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        /*
        Scanner s = new Scanner(new File("moocast.in"));
        int N = s.nextInt();
        */
        BufferedReader br = new BufferedReader(new FileReader(new File("moocast.in")));
        int N = Integer.parseInt(br.readLine());

        int[][] array = new int[N][2];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] splitLine = line.split(" ");
            array[i][0] = Integer.parseInt(splitLine[0]);
            array[i][1] = Integer.parseInt(splitLine[1]);
        }

        int index = 0;
        int[][] distanceArray = new int[N * (N - 1)/2][3];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                distanceArray[index][0] = (array[i][0] - array[j][0]) * (array[i][0] - array[j][0]) + (array[i][1] - array[j][1]) * (array[i][1] - array[j][1]);
                distanceArray[index][1] = i;
                distanceArray[index][2] = j;
                index += 1;
            }
        }

        Arrays.sort(distanceArray, (int[] a, int[] b) -> {
            return (a[0] - b[0]);
        });

        int ans = 0;

        UF unionFind = new UF(N);
        for (int i = 0; i < N * (N - 1)/2; i++) {
            int a = distanceArray[i][1];
            int b = distanceArray[i][2];
            if (!unionFind.isConnected(a, b)) {
                unionFind.merge(a, b);
                ans = distanceArray[i][0];
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        pw.println(ans);
        pw.close();

    }
}

class UF {
    int[] parents;  

    public UF(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public void merge(int a, int b) {
        parents[getRoot(a)] = getRoot(b);
    }

    public int getRoot(int a) {
        if (parents[a] == a) {
            return a;
        } else {
            parents[a] = getRoot(parents[a]);
            return getRoot(parents[a]);
        }
    }

    public boolean isConnected(int a, int b) {
        return (getRoot(a) == getRoot(b));
    }
}