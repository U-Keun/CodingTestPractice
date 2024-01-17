import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int c;
    private static final List<Long> leftHalf = new ArrayList<>();
    private static final List<Long> rightHalf = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int n = readInt();
        c = readInt();
        long[] left = new long[n / 2], right = new long[n - n / 2];
        for (int i = 0; i < n; i++) {
            if (i < n / 2) left[i] = readInt();
            else right[i - n / 2] = readInt();
        }
        computeCases(leftHalf, left, 0, 0);
        computeCases(rightHalf, right, 0, 0);

        Collections.sort(rightHalf);

        long count = 0;
        for (Long number : leftHalf) {
            long target = c - number;
            int index = binarySearch(target);
            if (index >= 0) count += index;
            else count += index;
        }
        System.out.println(count);
    }
    private static void computeCases(List<Long> list, long[] array, long current, int index) {
        if (index == array.length) {
            if (current <= c) {
                list.add(current);
            }
            return;
        }
        computeCases(list, array, current, index + 1);
        computeCases(list, array, current + array[index], index + 1);
    }
    private static int binarySearch(long key) {
        int low = 0, high = Main.rightHalf.size();
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (Main.rightHalf.get(mid) <= key) low = mid + 1;
            else high = mid;
        }
        return low;
    }
    private static int readInt() throws IOException {
        int c, n;
        boolean isNegative = false;
        c = System.in.read();
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        n = c & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return isNegative ? -n : n;
    }
}