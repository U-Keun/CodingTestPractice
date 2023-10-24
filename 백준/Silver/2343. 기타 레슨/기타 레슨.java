import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
            input = br.readLine().split(" ");
            int[] lectures = new int[N];
            int high = 0;
            for (int i = 0; i < N; i++) {
                lectures[i] = Integer.parseInt(input[i]);
                high += lectures[i];
            }
            int low = 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (check(lectures, mid, M)) low = mid + 1;
                else high = mid;
            }
            bw.write(String.valueOf(low));
            bw.flush();
        }
    }
    private static boolean check(int[] lectures, int capacity, int count) {
        int remain = capacity, blueRay = 1;
        for (int time:lectures) {
            if (time > capacity) return true;
            if (remain >= time) {
                remain -= time;
                continue;
            }
            remain = capacity - time;
            blueRay++;
        }
        return blueRay > count;
    }
}