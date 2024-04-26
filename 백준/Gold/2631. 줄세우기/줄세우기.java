import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] record;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        record = new int[N];
        Arrays.fill(record, N + 1);
        int index;
        for (int i = 0; i < N; i++) {
            index = Integer.parseInt(br.readLine());
            record[binarySearch(0, i, index)] = index;
        }
        br.close();
        int answer = N;
        for (int i = 0; i < N; i++) {
            if (record[i] < N + 1) answer--;
            else break;
        }
        System.out.println(answer);
    }
    private static int binarySearch(int left, int right, int target) {
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (record[mid] < target) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}