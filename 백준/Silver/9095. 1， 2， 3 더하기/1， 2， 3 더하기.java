import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] cases = new int[12];
        cases[0] = 1;
        cases[1] = 2;
        cases[2] = 4;
        int input;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            for (int j = 0; j < input; j++) {
                if (j <= 2 || cases[j] != 0) continue;
                cases[j] = cases[j - 1] + cases[j - 2] + cases[j - 3];
            }
            print.append(cases[input - 1]).append('\n');
        }
        System.out.println(print);
    }
}