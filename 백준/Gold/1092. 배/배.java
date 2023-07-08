import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, time = 0, n, m;
    static LinkedList<Integer> ship = new LinkedList<>(), boxes = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ship.add(Integer.parseInt(st.nextToken()));
        }
        ship.sort(Collections.reverseOrder());
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }
        boxes.sort(Collections.reverseOrder());
        Loop1 : while (!ship.isEmpty() && !boxes.isEmpty()) {
            n = ship.size();
            Loop2 : for (int i = 0; i < n; i++) {
                m = boxes.size();
                for (int j = 0; j < m; j++) {
                    if (ship.get(i) >= boxes.get(j)) {
                        boxes.remove(j);
                        continue Loop2;
                    } else if (j == m - 1) {
                        ship = new LinkedList<>(ship.subList(0, i));
                        time++;
                        continue Loop1;
                    }
                }
            }
            time++;
        }
        if (!boxes.isEmpty()) System.out.println(-1);
        else System.out.println(time);
    }
}