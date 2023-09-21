import java.io.*;
import java.util.*;

public class Main {
    static int[][] visited;
    static char[][] zone;
    static int safeZones = 0;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            zone = new char[N][M];
            for (int i = 0; i < N; i++) zone[i] = br.readLine().toCharArray();
            visited = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 0) dfs(i, j);
                }
            }
            bw.write(String.valueOf(safeZones));
            bw.flush();
        }
    }
    public static void dfs(int i, int j) {
        switch (visited[i][j]) {
            case 0:
                visited[i][j] = 1;
                switch (zone[i][j]) {
                    case 'U': dfs(i - 1, j); break;
                    case 'D': dfs(i + 1, j); break;
                    case 'L': dfs(i, j - 1); break;
                    case 'R': dfs(i, j + 1); break;
                }
                visited[i][j] = -1;
                break;
            case 1:
                safeZones++;
        }
    }
}