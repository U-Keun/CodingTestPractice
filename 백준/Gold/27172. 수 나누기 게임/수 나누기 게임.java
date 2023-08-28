import java.io.IOException;

public class Main {
    static StringBuilder print = new StringBuilder();
    static int N, k;
    static int[] input, score;
    static int[] numbers = new int[1000001];

    public static void main(String[] args) throws IOException {
        N = readInt();
        input = new int[N];
        score = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = readInt();
            numbers[input[i]] = i + 1;
        }
        for (int i = 0; i < N; i++) {
            k = input[i];
            for (int j = k + k; j <= 1000000 ; j += k) {
                if (numbers[j] != 0) {
                    score[i]++;
                    score[numbers[j] - 1]--;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            print.append(score[i]).append(" ");
        }
        System.out.println(print);
    }
    public static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}