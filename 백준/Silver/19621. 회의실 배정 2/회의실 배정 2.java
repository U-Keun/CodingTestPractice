import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()),
                    f = Integer.parseInt(st.nextToken()),
                    p = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(s, f, p));
        }
        br.close();

        Collections.sort(meetings);
        long[] dp = new long[N + 1];
        dp[1] = meetings.get(0).participants;
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + meetings.get(i - 1).participants);
        }
        System.out.println(dp[N]);
    }

    static class Meeting implements Comparable<Meeting> {
        int start, finish, participants;
        Meeting(int start, int finish, int participants) {
            this.start = start;
            this.finish = finish;
            this.participants = participants;
        }

        @Override
        public int compareTo(Meeting other) {
            return this.finish - other.finish;
        }
    }
}