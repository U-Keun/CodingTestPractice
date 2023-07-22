import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int W, H, N, row, col, l;
    static Block[][] map;
    static Queue<int[]> sites = new LinkedList<>();
    static List<Block> lamps = new ArrayList<>();
    static int[] rowIdx = {0, -1, 1, 0}, colIdx = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        map = new Block[H][W];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String blockType = st.nextToken();
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());
            switch (blockType) {
                case "redstone_block":
                    sites.add(new int[]{row, col});
                    map[row][col] = new Block(blockType);
                    map[row][col].setSignal(15);
                    break;
                case "redstone_dust":
                    map[row][col] = new Block(blockType);
                    break;
                case "redstone_lamp":
                    map[row][col] = new Block(blockType);
                    lamps.add(map[row][col]);
                    break;
            }
        }
        while (!sites.isEmpty()) {
            l = sites.size();
            for (int i = 0; i < l; i++) {
                int[] site = sites.poll();
                for (int j = 0; j < 4; j++) {
                    int newRow = site[0] + rowIdx[j];
                    int newCol = site[1] + colIdx[j];
                    if (newRow >= 0 && newRow < H
                            && newCol >= 0 && newCol < W
                            && map[newRow][newCol] != null
                            && map[newRow][newCol].signal < map[site[0]][site[1]].signal - 1) {
                        if (map[newRow][newCol].blockType.equals("redstone_dust")) sites.add(new int[]{newRow, newCol});
                        if (map[site[0]][site[1]].blockType.equals("redstone_block")) map[newRow][newCol].setSignal(15);
                        else map[newRow][newCol].setSignal(map[site[0]][site[1]].signal - 1);
                    }
                }
            }
        }
        boolean on = true;
        for (Block lamp:lamps) {
            if (lamp.signal == 0) {
                on = false;
                break;
            }
        }
        if (on) System.out.println("success");
        else System.out.println("failed");
    }
}
class Block {
    String blockType;
    int signal;
    public Block(String blockType) {
        this.blockType = blockType;
    }
    public void setSignal(int signal) {
        this.signal = signal;
    }
}