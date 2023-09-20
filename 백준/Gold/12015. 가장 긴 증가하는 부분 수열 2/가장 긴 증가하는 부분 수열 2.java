import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int[] input = new int[N], sequence = new int[N];
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(sequence, Integer.MAX_VALUE);
        sequence[0] = input[0];
        for (int i = 1; i < N; i++) {
            int idx = Arrays.binarySearch(sequence, input[i]);
            if (idx >= 0) sequence[idx] = input[i];
            else sequence[- idx - 1] = input[i];
        }
        int answer = 0;
        for (int k:sequence) {
            if (k == Integer.MAX_VALUE) break;
            answer++;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}