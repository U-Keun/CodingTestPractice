import java.io.IOException;

public class Main {
    private static int[] numbers = new int[5];
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            numbers[i] = readInt();
        }
        int answer = 4;
        while (!checkNumber(answer)) {
            answer++;
        }
        System.out.println(answer);
    }
    private static boolean checkNumber(int answer) {
        int count = 0;
        int idx = 0;
        while (idx < 5 && count < 3) {
            if (answer % numbers[idx] == 0) {
                count++;
            }
            idx++;
        }
        if (count == 3) return true;
        return false;
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