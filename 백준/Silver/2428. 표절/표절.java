import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] files = new int[N];
        for (int i = 0; i < N; i++) {
            files[i] = readInt();
        }
        Arrays.sort(files);
        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer += binarySearch(i, files) - i - 1;
        }
        System.out.println(answer);
    }
    private static int binarySearch(int i, int[] files) {
        int low = i, high = files.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (checkFile(files[i], files[mid])) low = mid + 1;
            else high = mid;
        }
        return low;
    }
    private static boolean checkFile(int small, int big) {
        if (small == big) return true;
        if (small * 10 >= big * 9) return true;
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