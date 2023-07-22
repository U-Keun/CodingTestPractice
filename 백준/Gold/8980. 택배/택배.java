import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, C, M, answer = 0;
    static int[] memo;
    static boolean[] parcelStop;
    static PriorityQueue<int[]> reservation = new PriorityQueue<>(
            Comparator.<int[]>comparingInt(a -> a[1]).thenComparingInt(a -> a[0]));

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        memo = new int[N + 1];
        parcelStop = new boolean[N + 1];
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            reservation.add(new int[]{a, b, c});
        }
        parcelStop[reservation.peek()[0]] = true;
        int[] tmp;
        int k;
        while (!reservation.isEmpty()) {
            tmp = reservation.poll();
            k = Integer.MAX_VALUE;
            if (!parcelStop[tmp[0]]) continue;
            parcelStop[tmp[1]] = true;

            for (int i = tmp[0]; i < tmp[1]; i++) {
                k = Math.min(k, Math.min(C - memo[i], tmp[2]));
            }
            for (int i = tmp[0]; i < tmp[1]; i++) {
                memo[i] += k;
            }
            answer += k;
        }
        System.out.println(answer);
    }
}