import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String T = br.readLine(), P = br.readLine();
            int[] record = record(P);
            int n = T.length(), m = P.length();
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0, j = 0; i < n; i++) {
                while (j > 0 && T.charAt(i) != P.charAt(j)) j = record[j - 1];
                if (T.charAt(i) == P.charAt(j)) {
                    if (j == m - 1) {
                        indices.add(i - m + 1);
                        j = record[j];
                    } else j++;
                }
            }
            StringBuilder print = new StringBuilder();
            print.append(indices.size()).append('\n');
            for (Integer i:indices) print.append(i + 1).append(" ");
            bw.write(print.toString());
            bw.flush();
        }
    }
    public static int[] record(String s) {
        int m = s.length();
        int[] answer = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = answer[j - 1];
            if (s.charAt(i) == s.charAt(j)) answer[i] = ++j;
        }
        return answer;
    }
}