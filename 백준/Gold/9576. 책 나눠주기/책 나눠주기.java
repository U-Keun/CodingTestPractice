import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] applicationList;
    private static int[] record;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int n, m;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            applicationList = new int[m + 1][2];
            record = new int[n + 1];

            for (int j = 1; j <= m; j++) {
                st = new StringTokenizer(br.readLine());
                applicationList[j][0] = Integer.parseInt(st.nextToken());
                applicationList[j][1] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            for (int j = 1; j <= m; j++) {
                visited = new boolean[n + 1];
                if (findMatching(j)) answer++;
            }
            print.append(answer).append("\n");
        }
        System.out.println(print);
    }

    private static boolean findMatching(int student) {
        for (int i = applicationList[student][0]; i <= applicationList[student][1]; i++) { // i : 책 번호
            if (visited[i]) continue;
            visited[i] = true;
            if (record[i] == 0 || findMatching(record[i])) {
                record[i] = student;
                return true;
            }
        }
        return false;
    }
}