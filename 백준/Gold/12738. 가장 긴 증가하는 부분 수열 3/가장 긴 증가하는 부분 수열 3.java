import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int[] input = new int[N], sequence = new int[N];
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(sequence, Integer.MAX_VALUE);
        sequence[0] = input[0];
        int answer = 0;
        for (int i = 1; i < N; i++) {
            if (sequence[answer] < input[i]) sequence[++answer] = input[i];
            else {
                int idx = binarySearch(sequence, answer, input[i]);
                sequence[idx] = input[i];
            }

        }
        bw.write(String.valueOf(answer + 1));
        bw.flush();
        bw.close();
    }
    public static int binarySearch(int[] arr, int end, int key) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < key) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}