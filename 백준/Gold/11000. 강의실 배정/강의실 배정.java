import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Lecture implements Comparable<Lecture> {
        int start, end;
        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Lecture other) {
            if (this.start == other.start) {
                return this.end - other.end;
            }
            return this.start - other.start;
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;
            PriorityQueue<Lecture> lectures = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
                lectures.add(new Lecture(s, t));
            }
            PriorityQueue<Integer> endTimes = new PriorityQueue<>();
            while (!lectures.isEmpty()) {
                Lecture tmp = lectures.poll();
                if (!endTimes.isEmpty() && endTimes.peek() <= tmp.start) {
                    endTimes.poll();
                }
                endTimes.add(tmp.end);
            }
            bw.write(String.valueOf(endTimes.size()));
            bw.flush();
        }
    }
}