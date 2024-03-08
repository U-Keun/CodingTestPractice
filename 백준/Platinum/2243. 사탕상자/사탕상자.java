import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree;
    private static final int MAX_CREDIT = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(MAX_CREDIT) / Math.log(2));
        tree = new int[2 << height];
        StringTokenizer st;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            if (q == 2) {
                int credit = Integer.parseInt(st.nextToken()), count = Integer.parseInt(st.nextToken());
                addCandies(1, 1, MAX_CREDIT, credit, count);
            } else {
                int rank = Integer.parseInt(st.nextToken());
                print.append(getCandyCredit(1, 1, MAX_CREDIT, rank)).append("\n");
            }
        }
        br.close();
        System.out.println(print);
    }
    private static void addCandies(int current, int start, int end, int credit, int count) {
        if (credit < start || credit > end) return;
        tree[current] += count;
        if (start == end) return;
        int mid = (start + end) >> 1;
        addCandies(current * 2, start, mid, credit, count);
        addCandies(current * 2 + 1, mid + 1, end, credit, count);
    }
    private static int getCandyCredit(int current, int start, int end, int rank) {
        tree[current]--;
       if (start == end) return start;
       int mid = (start + end) >> 1;
       if (tree[current * 2] >= rank) {
           return getCandyCredit(current * 2, start, mid, rank);
       } else return getCandyCredit(current * 2 + 1, mid + 1, end, rank - tree[current * 2]);
    }
}