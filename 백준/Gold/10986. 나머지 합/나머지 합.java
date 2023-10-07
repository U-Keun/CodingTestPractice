import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), sum = 0;
            int[] residues = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sum = (sum + Integer.parseInt(st.nextToken())) % M;
                residues[sum]++;
            }
            long count = residues[0];
            for (int i = 0; i < M; i++) {
                if (residues[i] != 0) {
                    count += (long) residues[i] * (residues[i] - 1) / 2;
                }
            }
            bw.write(String.valueOf(count));
            bw.flush();
        }
    }
}