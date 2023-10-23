import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            int[] requests = new int[N];
            String[] input = br.readLine().split(" ");
            int min = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                requests[i] = Integer.parseInt(input[i]);
                max = Math.max(max, requests[i]);
            }
            int M = Integer.parseInt(br.readLine());
            int mid = (min + max) >> 1;
            do {
                int check = checkBudget(requests, mid, M);
                if (check == 1) min = mid + 1;
                else if (check == -1) max = mid - 1;
                else break;
                mid = (min + max) >> 1;
            } while (min < max);

            if (checkBudget(requests, mid, M) < 0) mid--;

            bw.write(String.valueOf(mid));
            bw.flush();
        }
    }
    private static int checkBudget(int[] requests, int each, int bound) {
        int value = 0;
        for (int i:requests) {
            value += Math.min(each, i);
        }
        if (value < bound) return 1;
        else if (value > bound) return -1;
        return 0;
    }
}