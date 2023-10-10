import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] distance = new int[N - 1];
            for (int i = 0; i < N - 1; i++) distance[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] cost = new int[N];
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    cost[i] = value;
                    continue;
                }
                cost[i] = Math.min(cost[i - 1], value);
            }
            int total = 0;
            for (int i = 0; i < N - 1; i++) {
                total += distance[i] * cost[i];
            }
            bw.write(String.valueOf(total));
            bw.flush();
        }
    }
}