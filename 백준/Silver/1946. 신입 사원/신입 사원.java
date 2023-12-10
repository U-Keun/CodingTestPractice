import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static class Rank implements Comparable<Rank> {
        int paper, interview;
        Rank(int paper, int interview) {
            this.paper = paper;
            this.interview = interview;
        }
        @Override
        public int compareTo(Rank other) {
            return this.paper - other.paper;
        }
    }
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            Rank[] ranks = new Rank[N];
            for (int j = 0; j < N; j++) {
                ranks[j] = new Rank(readInt(), readInt());
            }
            Arrays.sort(ranks);
            int answer = 1;
            int standard = ranks[0].interview;
            for (int j = 1; j < N; j++) {
                if (ranks[j].interview < standard) {
                    answer++;
                    standard = ranks[j].interview;
                }
            }
            print.append(String.format("%d\n", answer));
        }
        System.out.println(print);
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
}