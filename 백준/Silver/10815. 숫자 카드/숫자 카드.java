import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < M; i++) {
            int left = 0, right = N - 1, pointer = (left + right) / 2,
                k = Integer.parseInt(st.nextToken());
            while (left < right) {
                if (k < numbers[pointer]) {
                    right = pointer - 1;
                } else if (k > numbers[pointer]) {
                    left = pointer + 1;
                } else {
                    left = pointer;
                    break;
                }
                pointer = (left + right) / 2;
            }
            if (k == numbers[left]) bw.write(1 + " ");
            else bw.write(0 + " ");
        }
        bw.flush();
        bw.close();
    }
}