import java.io.*;

public class Main {
    private static boolean[][] video;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            video = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (input[j] == '1') video[i][j] = true;
                }
            }
            bw.write(recurrence(N, 0, 0));
            bw.flush();
        }
    }
    private static String recurrence(int N, int i, int j) {
        if (N == 1) {
            if (video[i][j]) return "1";
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int k = 0; k < 4; k++) {
            sb.append(recurrence(N / 2, i + (N / 2) * (k / 2), j + (N / 2) * (k % 2)));
        }
        boolean isOne = true, isZero = true;
        for (int k = 1; k < sb.length(); k++) {
            if (sb.charAt(k) == '1') isZero = false;
            else isOne = false;
        }
        if (isOne) return "1";
        else if (isZero) return "0";
        sb.append(")");
        return sb.toString();
    }
}