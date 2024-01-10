import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[] numbers;
    private static boolean[] check;
    public static void main(String[] args) throws IOException {
        N = readInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = readInt();
        }
        Arrays.sort(numbers);
        check = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                search(i, j);
            }
        }
        int count = 0;
        for (boolean b : check) {
            if (b) count++;
        }
        System.out.println(count);
    }
    private static void search(int i, int j) {
        int low = 0, high = N, key = numbers[i] + numbers[j];
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (numbers[mid] <= key) low = mid + 1;
            else high = mid;
        }
        if (low > 0 && (key != numbers[low - 1] || check[low - 1])) return;
        if (low - 1 != i && low - 1 != j) {
            int index = low - 1;
            while (index >= 0 && key == numbers[index]) check[index--] = true;
            return;
        }
        if (low - 1 == j) {
            if (j - 1 == i) return;
            if (j > 1 && numbers[j] == numbers[j - 1]) {
                int index = low - 1;
                while (index >= 0 && key == numbers[index]) check[index--] = true;
            }
            return;
        }
        if (i > 0 && numbers[i] == numbers[i - 1]) {
            int index = low - 1;
            while (index >= 0 && key == numbers[index]) check[index--] = true;
        }
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c ==  '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}