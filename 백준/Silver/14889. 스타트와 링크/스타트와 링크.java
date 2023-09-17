import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, min = Integer.MAX_VALUE;
    static int[][] chemi;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        chemi = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                chemi[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        int start = 1 << (N / 2) - 1;
        for (int i = start; i <= start << (N / 2); i++) {
            if (Integer.bitCount(i) != N / 2) continue;
            ArrayList<Integer> A = new ArrayList<>(), B = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) A.add(j);
                else B.add(j);
            }
            min = Math.min(min, status(A, B));
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }
    public static int status(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        int k = N / 2, answer = 0;
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                answer += chemi[team1.get(i)][team1.get(j)] + chemi[team1.get(j)][team1.get(i)];
                answer -= chemi[team2.get(i)][team2.get(j)] + chemi[team2.get(j)][team2.get(i)];
            }
        }
        return Math.abs(answer);
    }
}