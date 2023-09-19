import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[N], sequence = new int[N];
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(sequence, Integer.MAX_VALUE);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (sequence[j] >= input[i]) {
                    sequence[j] = input[i];
                    idx = j;
                    break;
                }
            }
            int[] decSeq = new int[N - i];
            Arrays.fill(decSeq, Integer.MIN_VALUE);
            decSeq[0] = sequence[idx];
            for (int j = i + 1; j < N; j++) {
                for (int k = 0; k < N - i; k++) {
                    if (decSeq[k] <= input[j]) {
                        if (k == 0 && decSeq[k] < input[j]) decSeq[k + 1] = input[j];
                        else decSeq[k] = input[j];
                        break;
                    }
                }
            }
            if (sequence[idx] != decSeq[0]) idx++;
            for (int n:decSeq) {
                if (n > Integer.MIN_VALUE) idx++;
                else break;
            }
            max = Math.max(max, idx);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}