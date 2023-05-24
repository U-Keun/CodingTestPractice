import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] numbers;
    static int[] seqLengths;
    static int maxLength;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        seqLengths = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            seqLengths[i] = 1;
        }
        for (int i = N - 1; i > -1; i--) {
            if (i == N - 1) {
                seqLengths[i] = 1;
            } else {
                for (int j = i + 1; j < N; j++) {
                    if (numbers[i] < numbers[j] && seqLengths[i] < seqLengths[j] + 1) {
                        seqLengths[i] = seqLengths[j] + 1;
                    }
                }
            }
        }
        maxLength = 0;
        for (int i:seqLengths) {
            if (i > maxLength) maxLength = i;
        }
        System.out.println(maxLength);
    }
}