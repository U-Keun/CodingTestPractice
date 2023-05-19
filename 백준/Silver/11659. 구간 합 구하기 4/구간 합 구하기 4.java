import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] prefixSum = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == 0) prefixSum[i] = Integer.parseInt(st.nextToken());
            else prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }
        StringBuilder print = new StringBuilder();
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            if (a == 0) print.append(prefixSum[b]).append('\n');
            else print.append(prefixSum[b] - prefixSum[a - 1]).append('\n');
        }
        System.out.println(print);
    }
}