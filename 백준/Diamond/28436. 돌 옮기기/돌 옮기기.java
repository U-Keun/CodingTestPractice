import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            input = br.readLine().toCharArray();
            int l = input.length - 1;
            while (input[l] == '.') l--;

            char current = input[l];
            int credit = (current == 'W') ? 1 : -1;

            long score = 0;
            while (--l >= 0) {
                if (input[l] == '.') {
                    score += credit;
                    continue;
                }

                if (current == input[l]) {
                    credit += (current == 'W') ? 1 : -1;
                    continue;
                }

                l--;
                while (l >= 0 && input[l] == '.') l--;
                if (l < 0) break;
                current = input[l];
                credit = (current == 'W') ? 1 : -1;

            }

            if (score > 0) print.append("WHITE\n");
            else print.append("BLACK\n");

        }
        br.close();
        System.out.println(print);
    }
}