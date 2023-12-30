import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        int T = readInt();
        Map<Integer, List<Integer>> residues = new HashMap<>();
        residues.put(1, List.of(1));
        residues.put(2, List.of(2, 4, 8, 6));
        residues.put(3, List.of(3, 9, 7, 1));
        residues.put(4, List.of(4, 6));
        residues.put(5, List.of(5));
        residues.put(6, List.of(6));
        residues.put(7, List.of(7, 9, 3, 1));
        residues.put(8, List.of(8, 4, 2, 6));
        residues.put(9, List.of(9, 1));
        residues.put(10, List.of(10));
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int a = readInt(), b = readInt();
            if (a % 10 == 0) {
                a = 10;
            } else a %= 10;
            if (residues.get(a).size() == 1) {
                print.append(residues.get(a).get(0));
            } else {
                int r = b % residues.get(a).size();
                int tmp = (r == 0) ? residues.get(a).size() - 1 : r - 1;
                print.append(residues.get(a).get(tmp));
            }
            print.append("\n");
        }
        System.out.println(print);
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