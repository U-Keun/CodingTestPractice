import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), L = readInt();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            positions.add(readInt());
        }
        Collections.sort(positions);
        int tapes = 1, idx = 0, tapeStart = 0;
        while (idx < N - 1) {
            idx++;
            if (positions.get(idx) - positions.get(tapeStart) >= L) {
                tapes++;
                tapeStart = idx;
            }
        }
        System.out.println(tapes);
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