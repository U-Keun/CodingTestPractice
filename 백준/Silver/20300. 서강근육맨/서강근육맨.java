import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Long[] numbers = new Long[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Long.parseLong(st.nextToken());
            }
            Arrays.sort(numbers, Comparator.reverseOrder());
            int l = N / 2 + N % 2;
            long[] record = new long[l];
            for (int i = 0; i < N; i++) {
                record[i < l ? i : 2 * l - i - 1] += numbers[i];
            }
            long max = Long.MIN_VALUE;
            for (int i = 0; i < l; i++) {
                max = Math.max(max, record[i]);
            }
            bw.write(String.valueOf(max));
            bw.flush();
        }
    }
}