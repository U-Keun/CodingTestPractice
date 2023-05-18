import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] weights = new int[N];
        int[] heights = new int[N];
        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            weights[i] = Integer.parseInt(input[0]);
            heights[i] = Integer.parseInt(input[1]);
        }
        int count;
        for (int i = 0; i < N; i++) {
            count = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (weights[i] < weights[j] && heights[i] < heights[j]) count++;
            }
            print.append(count + 1).append(" ");
        }
        System.out.println(print);
    }
}