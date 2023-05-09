import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int input;
        int[][] fibonacci = new int[41][2];
        fibonacci[0][0] = 1;
        fibonacci[0][1] = 0;
        fibonacci[1][0] = 0;
        fibonacci[1][1] = 1;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            if (input == 0) {
                print.append(fibonacci[0][0] + " " + fibonacci[0][1]).append('\n');
            } else if (input == 1) {
                print.append(fibonacci[1][0] + " " + fibonacci[1][1]).append('\n');
            } else {
                for (int j = 2; j <= input; j++) {
                    if (fibonacci[j][0] == 0 && fibonacci[j][1] == 0) {
                        fibonacci[j][0] = fibonacci[j - 1][0] + fibonacci[j - 2][0];
                        fibonacci[j][1] = fibonacci[j - 1][1] + fibonacci[j - 2][1];
                    }
                }
                print.append(fibonacci[input][0] + " " + fibonacci[input][1]).append('\n');
            }
        }
        System.out.println(print);
    }
}