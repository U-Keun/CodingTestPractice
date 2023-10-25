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
            StringBuilder print = new StringBuilder();
            int[] list;
            StringTokenizer st;
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(br.readLine());
                list = new int[n];
                for (int j = 0; j < n; j++) {
                    list[j] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(list);
                print.append(countCombinations(list, k)).append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
    private static int countCombinations(int[] list, int target) {
        int n = list.length, count = 0, diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int nextTarget = target - list[i], low = i + 1, high = n - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (list[mid] <= nextTarget) low = mid + 1;
                else high = mid;
            }

            int check = Math.abs(nextTarget - list[low]);
            if (i + 1 != low) {
                check = Math.min(check, Math.abs(nextTarget - list[low - 1]));
            }

            if (check < diff) {
                diff = check;
                count = 1;
            } else if (check == diff) {
                count++;
            }
        }
        return count;
    }
}