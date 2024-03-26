import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ramens = readIntegers(br.readLine());
        br.close();
        long answer = 0;
        for (int i = 0; i < N; i++) {
            if (ramens[i] == 0)
                continue;

            if (i == N - 1) { // 마지막 공장에 남아있는 라면 처리
                answer += ramens[i] * 3L;
                ramens[i] = 0;
                continue;
            }

            if (i == N - 2) { //  마지막에서 두 번째 공장에 남아있는 라면 처리
                int val = Math.min(ramens[i], ramens[i + 1]);
                answer += val * 5L;
                ramens[i] -= val;
                ramens[i + 1] -= val;
                answer += ramens[i] * 3L;
                ramens[i] = 0;
                continue;
            }

            if (ramens[i + 1] > ramens[i + 2]) {
                // i + 1번째, i + 2번째 공장의 남은 라면 개수를 최대한 맞춰준다. -> 결과적으로 i + 2번째 공장의 라면이 i + 1번째 공장보다 많거나 같다.
                int val = Math.min(ramens[i + 1] - ramens[i + 2], ramens[i]);
                answer += val * 5L;
                ramens[i + 1] -= val;
                ramens[i] -= val;
            }

            // i번째 공장부터 연속 3개의 공장에서 동시에 라면을 최대한 산다.
            int val = Math.min(ramens[i], ramens[i + 1]);
            answer += val * 7L;
            ramens[i] -= val;
            ramens[i + 1] -= val;
            ramens[i + 2] -= val;

            // i번째 공장에 남아 있는 라면을 산다.
            answer += ramens[i] * 3L;
            ramens[i] = 0;
        }
        System.out.println(answer);
    }

    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}