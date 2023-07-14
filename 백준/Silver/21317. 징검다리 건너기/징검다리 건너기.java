import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, min = Integer.MAX_VALUE;
    static int[][] energy;
    static Queue<MemoPair>[] memo;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        energy = new int[N - 1][2];
        memo = new Queue[N - 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            energy[i][0] = Integer.parseInt(st.nextToken());
            energy[i][1] = Integer.parseInt(st.nextToken());
            memo[i] = new LinkedList<>();
        }
        K = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < N - 1; i++) {
            if (i == 0) {
                for (int j = 0; j < 3; j++) {
                    if (i + j >= N - 1) break;
                    else if (j != 2) memo[i + j].add(new MemoPair(energy[i][j], false));
                    else memo[i + j].add(new MemoPair(K, true));
                }
            } else {
                while (!memo[i - 1].isEmpty()) {
                    MemoPair tmp = memo[i - 1].poll();
                    for (int j = 0; j < 3; j++) {
                        if (i + j >= N - 1) break;
                        else if (j != 2) memo[i + j].add(new MemoPair(tmp.value + energy[i][j], tmp.superJump));
                        else if (!tmp.superJump) memo[i + j].add(new MemoPair(tmp.value + K, true));
                    }
                }
            }
        }
        for (MemoPair pair:memo[N - 2]) {
            min = Math.min(min, pair.value);
        }
        System.out.println(min);
    }
}
class MemoPair {
    int value;
    boolean superJump;
    public MemoPair(int value, boolean superJump) {
        this.value = value;
        this.superJump = superJump;
    }
}