import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), capacity = readInt();
        int answer = 0, tmp = 0;
        for (int i = 0; i < N; i++) {
            int book = readInt();
            if (tmp >= book) {
                tmp -= book;
            } else {
                answer++;
                tmp = capacity - book;
            }
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