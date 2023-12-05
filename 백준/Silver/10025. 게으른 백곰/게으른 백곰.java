import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt();
        int[] ices = new int[1000001];
        int max = 0;
        for (int i = 0; i < N; i++) {
            int ice = readInt(), position = readInt();
            ices[position] = ice;
            max = Math.max(max, position);
        }
        int start = 0, end = 2 * K, sum = 0;
        for (int i = start; i <= Math.min(end, 1000000); i++) {
            sum += ices[i];
        }
        int answer = sum;
        while (end <= max) {
            answer = Math.max(answer, sum);
            sum -= ices[start++];
            sum += ices[++end];
        }
        System.out.println(answer);
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