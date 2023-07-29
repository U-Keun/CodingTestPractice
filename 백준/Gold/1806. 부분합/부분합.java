import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S, p1 = 0, p2 = 1, value, length = Integer.MAX_VALUE;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        setVariables();
        while (p1 <= N - 1 && p2 <= N) {
            value = numbers[p2] - numbers[p1];
            if (value < S) p2++;
            else if (value > S) {
                length = Math.min(length, p2 - p1);
                p1++;
            }
            else {
                length = Math.min(length, p2 - p1);
                p2++;
            }
        }
        if (length == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(length);
    }
    static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i + 1] = numbers[i] + Integer.parseInt(st.nextToken());
        }
    }
}