import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        int[] record = new int[N];
        for (int i = 0; i < N; i++) {
            int index = 0;
            while (record[index] != 0 && record[index] < numbers[i]) {
                index++;
            }
            record[index] = numbers[i];
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (record[i] == 0) break;
            answer++;
        }

        System.out.println(answer);
    }
}