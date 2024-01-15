import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), S = readInt();
        int[] left = new int[N / 2], right = new int[N - N / 2];
        for (int i = 0; i < N; i++) {
            if (i < N / 2) left[i] = readInt();
            else right[i - N / 2] = readInt();
        }
        System.out.println(countPair(subArraySums(left), subArraySums(right), S));
    }
    private static List<Integer> subArraySums(int[] array) {
        int l = array.length;
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < (1 << l); i++) {
            int sum = 0;
            for (int j = 0; j < l; j++) {
                if ((i & (1 << j)) != 0) sum += array[j];
            }
            sums.add(sum);
        }
        Collections.sort(sums);
        return sums;
    }
    private static long countPair(List<Integer> leftSums, List<Integer> rightSums, int target) {
        int i = 0, j = rightSums.size() - 1;
        long count = (target == 0)? -1 : 0;
        while (i < leftSums.size() && j >= 0) {
            int sum = leftSums.get(i) + rightSums.get(j);
            if (sum == target) {
                int tmpI = i, tmpJ = j;
                long leftDuplication = 0, rightDuplication = 0;
                while (i < leftSums.size() && Objects.equals(leftSums.get(i), leftSums.get(tmpI))) {
                    i++;
                    leftDuplication++;
                }
                while (j >= 0 && Objects.equals(rightSums.get(j), rightSums.get(tmpJ))) {
                    j--;
                    rightDuplication++;
                }
                count += leftDuplication * rightDuplication;
            } else if (sum > target) j--;
            else i++;
        }
        return count;
    }
    private static int readInt() throws IOException {
        int c, n = 0;
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