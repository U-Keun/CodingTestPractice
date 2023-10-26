import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            int[] numbers = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers);
            int x = Integer.parseInt(br.readLine());
            int i = 0, j = n - 1, count = 0;
            while (i < j) {
                int value = numbers[i] + numbers[j];
                if (value > x) {
                    j--;
                    continue;
                }
                i++;
                if (value == x) count++;
            }
            bw.write(String.valueOf(count));
            bw.flush();
        }
    }
}