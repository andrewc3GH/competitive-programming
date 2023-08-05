import java.io.*;
import java.util.*;

class Main {  
    public static void main(String[] args) throws IOException {
        
        Scanner s = new Scanner(new File("auto.in"));
        int W = s.nextInt();
        int N = s.nextInt();

        String[][] dictionary = new String[W][2];
        for (int i = 0; i < W; i++) {
            dictionary[i][0] = s.next();
            dictionary[i][1] = Integer.toString(i);
        }

        HashMap<String, Integer> convert = new HashMap<>();
        convert.put("a", 0);
        convert.put("b", 1);
        convert.put("c", 2);
        convert.put("d", 3);
        convert.put("e", 4);
        convert.put("f", 5);
        convert.put("g", 6);
        convert.put("h", 7);
        convert.put("i", 8);
        convert.put("j", 9);
        convert.put("k", 10);
        convert.put("l", 11);
        convert.put("m", 12);
        convert.put("n", 13);
        convert.put("o", 14);
        convert.put("p", 15);
        convert.put("q", 16);
        convert.put("r", 17);
        convert.put("s", 18);
        convert.put("t", 19);
        convert.put("u", 20);
        convert.put("v", 21);
        convert.put("w", 22);
        convert.put("x", 23);
        convert.put("y", 24);
        convert.put("z", 25);


        Arrays.sort(dictionary, (String[] a, String[] b) -> {
            return a[0].compareTo(b[0]);
        });
        

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            int addOn = s.nextInt();
            String str = s.next();
            boolean works = true;

            int low = 0;
            int high = W - 1;
            int middle = -1;
            int old = -2;
            while (old != middle) {
                old = middle;
                middle = (low + high)/2;

                if (str.compareTo(dictionary[middle][0]) > 0) {
                    low = middle + 1;
                } else {
                    high = middle;
                }

                /*for (int j = 0; j < str.length() && j < dictionary[middle][0].length(); j++) {
                    if (convert.get(Character.toString(str.charAt(j))) > convert.get(Character.toString(dictionary[middle][0].charAt(j)))) {
                        low = middle;
                        break;
                    } else {
                        high = middle;
                        break;
                    }
                }*/

            }
            //System.out.println(middle + " " + low + " " + high);
            /*
            for (int j = middle; j < W; j++) {
                if (dictionary[j][0].length() >= str.length()) {
                    middle = j;
                    break;
                }
            }
            */
            /*
            for (int j = 0; j < str.length() && j < dictionary[middle][0].length(); j++) {
                if (convert.get(Character.toString(str.charAt(j))) != convert.get(Character.toString(dictionary[middle][0].charAt(j)))) {
                    firstWorks = false;
                }
            }
            */

            int newIndex = 0;
            newIndex = middle + addOn - 1;

            if (works && newIndex < W) {
                if (!dictionary[newIndex][0].startsWith(str)) {
                    works = false;
                }
            } else {
                works = false;
            }

            if (works) {
                ans[i] = Integer.parseInt(dictionary[newIndex][1]) + 1;
            } else {
                ans[i] = -1;
            }
        }
        
        //System.out.println(Arrays.toString(ans));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
        for (int i = 0; i < N; i++) {
            pw.println(ans[i]);
        }

        pw.close();

    }
}