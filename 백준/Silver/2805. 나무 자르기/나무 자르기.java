import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, left, mid, right;
    static int[] trees;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        trees = new int[N];
        left = 0;
        right = 0;
        for (int i = 0; i < N; i++) {
            mid = Integer.parseInt(st.nextToken());
            if (mid > right) right = mid;
            trees[i] = mid;
        }
        while (left < right) {
            mid = (left + right) / 2;
            if (chips(mid) >= M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (chips(left - 1) >= M) System.out.println(left - 1);
        else System.out.println(left);
    }
    static long chips(int mid) {
        long answer = 0;
        for (int i:trees) {
            if (i - mid > 0) answer += i - mid;
        }
        return answer;
    }
}