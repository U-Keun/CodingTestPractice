import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int answer = 0;
        for (int i = N; i > 100; i--) {
            int number = i;

            int a = number % 10;
            number /= 10;
            int d = a - number % 10;
            a = number % 10;
            number /= 10;

            boolean check = true;
            while (check && number != 0) {
                if (a - d != number % 10) {
                    check = false;
                    break;
                }

                a = number % 10;
                number /= 10;
            }
            if (check) answer++;
        }
        if (N > 99) {
            answer += 99;
        } else answer += N;

        System.out.println(answer);
    }
}