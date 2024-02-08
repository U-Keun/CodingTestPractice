import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String input;
        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            startGame(br, bw, N, K);
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    private static int[] tree;
    private static void startGame(BufferedReader reader, BufferedWriter writer,
                                  int numbers, int commands) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] array = new int[numbers];
        int height = (int) Math.ceil(Math.log(numbers) / Math.log(2)), treeLength = 2 << height;
        tree = new int[treeLength];
        for (int i = 0; i < numbers; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        generate(array, 1, 0, numbers - 1);
        for (int i = 0; i < commands; i++) {
            st = new StringTokenizer(reader.readLine());
            int a, b;
            if (st.nextToken().equals("C")) {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                update(1, 0, numbers - 1, a - 1, b);
                continue;
            }
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int answer = getIntervalSign(1, 0, numbers - 1, a - 1, b - 1);
            if (answer > 0) writer.write("+");
            else if (answer < 0) writer.write("-");
            else writer.write("0");
        }

    }
    private static int generate(int[] array, int current, int start, int end) {
        if (start == end) {
            return tree[current] = (int) Math.signum(array[start]);
        }
        int mid = (start + end) >> 1;
        return tree[current] = generate(array, current * 2, start, mid)
                * generate(array, current * 2 + 1, mid + 1, end);
    }
    private static int getIntervalSign(int current, int start, int end, int left, int right) {
        if (left > end || right < start) return 1;
        if (left <= start && right >= end) return tree[current];
        int mid = (start + end) >> 1;
        return getIntervalSign(current * 2, start, mid, left, right)
                * getIntervalSign(current * 2 + 1, mid + 1, end, left, right);
    }
    private static void update(int current, int start, int end, int index, int number) {
        if (index < start || index > end) return;
        if (start == end) {
            tree[current] = (int) Math.signum(number);
            return;
        }
        int mid = (start + end) >> 1;
        update(current * 2, start, mid, index, number);
        update(current * 2 + 1, mid + 1, end, index, number);
        tree[current] = tree[current * 2] * tree[current * 2 + 1];
    }
}