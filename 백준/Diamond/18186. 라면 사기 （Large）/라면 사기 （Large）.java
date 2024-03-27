import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = readIntegers(br.readLine());
        long[] ramens = readIntegers(br.readLine());
        br.close();
        if (input[1] <= input[2]) {
            System.out.println(Arrays.stream(ramens).sum() * input[1]);
            return;
        }
        long answer = 0;
        for (int i = 0; i < input[0]; i++) {
            if (ramens[i] == 0)
                continue;

            if (i == input[0] - 1) { // 마지막 공장에 남아있는 라면 처리
                answer += ramens[i] * input[1];
                ramens[i] = 0;
                continue;
            }

            if (i == input[0] - 2) { //  마지막에서 두 번째 공장에 남아있는 라면 처리
                long val = Math.min(ramens[i], ramens[i + 1]);
                answer += val * (input[1] + input[2]);
                ramens[i] -= val;
                ramens[i + 1] -= val;
                answer += ramens[i] * input[1];
                ramens[i] = 0;
                continue;
            }

            if (ramens[i + 1] > ramens[i + 2]) {
                // i + 1번째, i + 2번째 공장의 남은 라면 개수를 최대한 맞춰준다. -> 결과적으로 i + 2번째 공장의 라면이 i + 1번째 공장보다 많거나 같다.
                long val = Math.min(ramens[i + 1] - ramens[i + 2], ramens[i]);
                answer += val * (input[1] + input[2]);
                ramens[i + 1] -= val;
                ramens[i] -= val;
            }

            // i번째 공장부터 연속 3개의 공장에서 동시에 라면을 최대한 산다.
            long val = Math.min(ramens[i], ramens[i + 1]);
            answer += val * (input[1] + input[2] * 2L);
            ramens[i] -= val;
            ramens[i + 1] -= val;
            ramens[i + 2] -= val;

            // i번째 공장에 남아 있는 라면을 산다.
            answer += ramens[i] * input[1];
            ramens[i] = 0;
        }
        System.out.println(answer);
    }

    private static long[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
    }
}