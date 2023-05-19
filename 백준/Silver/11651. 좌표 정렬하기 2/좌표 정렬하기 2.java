import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    static int getX(Pair pair) {
        return pair.x;
    }
    static int getY(Pair pair) {
        return pair.y;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input;
        List<Pair> pairList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            pairList.add(new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        StringBuilder print = new StringBuilder();
        pairList.sort(Comparator.comparing(Pair::getY).thenComparing(Pair::getX));
        for (Pair pair:pairList) {
            print.append(Pair.getX(pair) + " " + Pair.getY(pair)).append('\n');
        }
        System.out.println(print);
    }
}