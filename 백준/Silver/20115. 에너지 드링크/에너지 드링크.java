import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            Integer[] numbers = new Integer[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers, Collections.reverseOrder());
            double answer = numbers[0];
            for (int i = 1; i < N; i++) {
                answer += (double) numbers[i] / 2;
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }
    }
}