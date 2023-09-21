import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
            char[][] zone = new char[N][M];
            int[][][] parent = new int[N][M][2];
            for (int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    zone[i][j] = input[j];
                    Arrays.fill(parent[i][j], -1);
                }
            }
            Queue<int[]> moving = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];
            ArrayList<int[]> nonSafeZones = new ArrayList<>();
            HashSet<int[]> safeZones = new HashSet<>();
            int[] check = {-1, -1};
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) continue;
                    visited[i][j] = true;
                    nonSafeZones.add(new int[]{i, j});
                    moving.add(nonSafeZones.get(0));
                    int[] candidate = {i, j};
                    while (!moving.isEmpty()) {
                        int[] idx = moving.poll();
                        switch (zone[idx[0]][idx[1]]) {
                            case 'U':
                                if (!visited[idx[0] - 1][idx[1]]) {
                                    candidate[0] = idx[0] - 1;
                                    candidate[1] = idx[1];
                                    visited[candidate[0]][candidate[1]] = true;
                                    nonSafeZones.add(new int[]{candidate[0], candidate[1]});
                                    moving.add(nonSafeZones.get(nonSafeZones.size() - 1));
                                } else if (!Arrays.equals(parent[idx[0] - 1][idx[1]], check)) {
                                    candidate = parent[idx[0] - 1][idx[1]];
                                }
                                break;
                            case 'D':
                                if (!visited[idx[0] + 1][idx[1]]) {
                                    candidate[0] = idx[0] + 1;
                                    candidate[1] = idx[1];
                                    visited[candidate[0]][candidate[1]] = true;
                                    nonSafeZones.add(new int[]{candidate[0], candidate[1]});
                                    moving.add(nonSafeZones.get(nonSafeZones.size() - 1));
                                } else if (!Arrays.equals(parent[idx[0] + 1][idx[1]], check)) {
                                    candidate = parent[idx[0] + 1][idx[1]];
                                }
                                break;
                            case 'L':
                                if (!visited[idx[0]][idx[1] - 1]) {
                                    candidate[0] = idx[0];
                                    candidate[1] = idx[1] - 1;
                                    visited[candidate[0]][candidate[1]] = true;
                                    nonSafeZones.add(new int[]{candidate[0], candidate[1]});
                                    moving.add(nonSafeZones.get(nonSafeZones.size() - 1));
                                } else if (!Arrays.equals(parent[idx[0]][idx[1] - 1], check)) {
                                    candidate = parent[idx[0]][idx[1] - 1];
                                }
                                break;
                            case 'R':
                                if (!visited[idx[0]][idx[1] + 1]) {
                                    candidate[0] = idx[0];
                                    candidate[1] = idx[1] + 1;
                                    visited[candidate[0]][candidate[1]] = true;
                                    nonSafeZones.add(new int[]{candidate[0], candidate[1]});
                                    moving.add(nonSafeZones.get(nonSafeZones.size() - 1));
                                } else if (!Arrays.equals(parent[idx[0]][idx[1] + 1], check)) {
                                    candidate = parent[idx[0]][idx[1] + 1];
                                }
                                break;
                        }
                    }
                    for (int[] idx:nonSafeZones) {
                        parent[idx[0]][idx[1]] = candidate;
                    }
                    safeZones.add(candidate);
                    nonSafeZones.clear();
                }
            }
            bw.write(String.valueOf(safeZones.size()));
            bw.flush();
        }

    }
}