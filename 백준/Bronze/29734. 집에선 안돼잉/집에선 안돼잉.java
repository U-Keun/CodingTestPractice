import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        long N = readLong(), M = readLong(), T = readLong(), S = readLong(), zip = N, dok = M;
        if (N % 8 == 0) zip += (N / 8 - 1) * S;
        else zip += (N / 8) * S;
        if (M % 8 == 0) dok += (M / 8) * T + (M / 8 - 1) * (T + S);
        else dok += (M / 8 + 1) * T + (M / 8) * (T + S);
        
        StringBuilder print = new StringBuilder();
        if (zip > dok) {
            print.append("Dok").append('\n').append(dok);
        } else {
            print.append("Zip").append('\n').append(zip);
        }
        System.out.println(print);
    }
    public static long readLong() throws IOException {
        long val = 0;
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