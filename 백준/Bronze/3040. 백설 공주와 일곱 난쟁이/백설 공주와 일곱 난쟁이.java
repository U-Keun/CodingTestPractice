import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] numbers = new int[9];
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                numbers[i] = Integer.parseInt(br.readLine());
                sum += numbers[i];
            }

            for (int i = 0; i < 8; i++) {
                for (int j = i + 1; j < 9; j++) {
                    if (sum - 100 == numbers[i] + numbers[j]) {
                        StringBuilder print = new StringBuilder();
                        for (int k = 0; k < 9; k++) {
                            if (k == i || k == j) continue;
                            print.append(String.format("%d\n", numbers[k]));
                        }
                        bw.write(print.toString());
                        bw.flush();
                        return;
                    }
                }
            }

        }
    }
}