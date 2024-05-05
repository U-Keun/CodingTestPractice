import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] ESM = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answer = 1;
        while (ESM[0] != 1 || ESM[1] != 1 || ESM[2] != 1) {
            answer++;
            ESM[0]--;
            ESM[1]--;
            ESM[2]--;
            if (ESM[0] == 0) ESM[0] += 15;
            if (ESM[1] == 0) ESM[1] += 28;
            if (ESM[2] == 0) ESM[2] += 19;
        }
        System.out.println(answer);
    }
}