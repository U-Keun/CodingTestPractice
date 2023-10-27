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
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st;
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
                int[] A = new int[N], B = new int[M];
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) A[j] = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) B[j] = Integer.parseInt(st.nextToken());
                Arrays.sort(B);
                bw.write(String.valueOf(countPairs(A, B)));
                bw.write("\n");
            }
            bw.flush();
        }
    }
    private static int countPairs(int[] A, int[] B) {
        int count = 0, M = B.length;
        for (int a:A) {
            int idx = binarySearch(B, a);
            count += idx;
            if (B[idx] < a) count++;
        }
        return count;
    }
    private static int binarySearch(int[] B, int target) {
        int low = 0, high = B.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (B[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}