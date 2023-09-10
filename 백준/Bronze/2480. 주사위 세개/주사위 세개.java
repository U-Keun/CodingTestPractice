import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt(), C = readInt(), prize = 0;
        if (A == B && B == C) {
            prize = 10000 + A * 1000;
        } else if (A == B || A == C) {
            prize = 1000 + A * 100;
        } else if (B == C) {
            prize = 1000 + B * 100;
        } else {
            prize = Math.max(A, Math.max(B, C)) * 100;
        }
        System.out.println(prize);
    }
    public static int readInt() throws IOException {
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