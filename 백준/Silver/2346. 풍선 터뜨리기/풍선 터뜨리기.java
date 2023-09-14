import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Balloon[] balloons = new Balloon[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            balloons[i] = new Balloon(i + 1, Integer.parseInt(st.nextToken()));
            if (i > 0) {
                balloons[i].prev = balloons[i - 1];
                balloons[i - 1].next = balloons[i];
            }
        }
        balloons[0].prev = balloons[N - 1];
        balloons[N - 1].next = balloons[0];
        Balloon current = balloons[0];
        for (int i = 0; i < N; i++) {
            bw.write(current.value + " ");
            int steps = current.steps;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            if (steps > 0) {
                while (steps-- > 0) current = current.next;
            } else {
                while (steps++ < 0) current = current.prev;
            }
        }
        bw.flush();
        bw.close();
    }
}
class Balloon {
    int value, steps;
    Balloon prev, next;
    public Balloon(int value, int steps) {
        this.value = value;
        this.steps = steps;
        this.prev = null;
        this.next = null;
    }
}