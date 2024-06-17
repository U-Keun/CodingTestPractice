import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = readIntegers(br.readLine()), B = readIntegers(br.readLine());
        br.close();

        int index = N - 1;
        while (index >= 0 && A[index] == B[index]) {
            index--;
        }

        if (index < 0) {
            System.out.println(1);
            return;
        }

        for (int i = N - 1; i > 0; i--) {
            int maxIdx = getMaxIdx(A, i);
            if (i != maxIdx) {
                int tmp = A[i];
                A[i] = A[maxIdx];
                A[maxIdx] = tmp;
            } else continue;

            boolean flag = true;
            for (int j = 0; j < N; j++) {
                if (A[j] != B[j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int getMaxIdx(int[] arr, int lastIdx) {
        int max = 0, maxIdx = 0;
        for (int i = 0; i <= lastIdx; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}