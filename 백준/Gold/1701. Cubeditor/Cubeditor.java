import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = br.readLine();
            int m = s.length(), maxLength = Integer.MIN_VALUE;
            int[][] record = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 1, k = 0; j < m - i; j++) {
                    while (k > 0 && s.charAt(j + i) != s.charAt(k + i)) k = record[i][k - 1];
                    if (s.charAt(j + i) == s.charAt(k + i)) {
                        record[i][j] = ++k;
                        maxLength = Math.max(maxLength, record[i][j]);
                    }
                }
            }
            if (maxLength == Integer.MIN_VALUE) bw.write(String.valueOf(0));
            else bw.write(String.valueOf(maxLength));
            bw.flush();
        }
    }
}