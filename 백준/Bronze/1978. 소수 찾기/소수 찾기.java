import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        int input;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(st.nextToken());
            if (input == 1) continue;
            else if (isPrime(input)) count++;
        }
        System.out.println(count);
    }
    static boolean isPrime(int k) {
        boolean answer = true;
        for (int i = 2; i <= Math.sqrt(k); i++) {
            if (k % i == 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}