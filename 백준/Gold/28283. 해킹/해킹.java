import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X, Y;
    static int[] reward;
    static List<Integer>[] graph;
    static boolean[] visited;
    static Queue<Integer> secured = new LinkedList<>();
    static long[] expReward;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        reward = new int[N];
        graph = new ArrayList[N];
        visited = new boolean[N];
        expReward = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 해킹 보상 기록 및 그래프 초기화
            reward[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < M; i++) { // 그래프(네트워크) 만들기
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph[a - 1].add(b);
            graph[b - 1].add(a);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Y; i++) { // 다음에 보안 시스템이 설치될 컴퓨터 번호 기록
            int k = Integer.parseInt(st.nextToken());
            visited[k - 1] = true;
            secured.add(k);
        }
        int k = 1;
        while (!secured.isEmpty()) {
            int l = secured.size();
            for (int i = 0; i < l; i++) {
                int tmp = secured.poll();
                for (Integer j:graph[tmp - 1]) {
                    if (!visited[j - 1]) {
                        visited[j - 1] = true;
                        expReward[j - 1] = (long) k * reward[j - 1];
                        secured.add(j);
                    }
                }
            }
            k++;
        }
        Arrays.sort(expReward);
        boolean allComSecured = true;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && reward[i] != 0) {
                allComSecured = false;
                break;
            }
        }
        long money = 0;
        if (allComSecured) {
            for (int i = 0; i < X; i++) {
                money += expReward[N - i - 1];
            }
            System.out.println(money);
        } else System.out.println(-1);
    }
}