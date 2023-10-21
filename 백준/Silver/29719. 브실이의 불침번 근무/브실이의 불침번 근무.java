import java.io.IOException;

public class Main {
    private static int divisor = 1000000007;
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        long a = modPower(M, N), b = modPower(M - 1, N);
        if (a >= b) System.out.println(a - b);
        else System.out.println(a - b + divisor);
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
    private static long modPower(long a, long n) {
	long answer = 1;
	while (n > 0) {
		if (n % 2 == 1) {
			answer = (answer * a) % divisor;
		}
		a = (a * a) % divisor;
		n >>= 1;
	}
	return answer;
}
}