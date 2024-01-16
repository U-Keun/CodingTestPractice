import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static int n;
    public static void main(String[] args) throws IOException {
        n = readInt();
        int[][] numbers = new int[4][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[j][i] = readInt();
            }
        }
        int[] AB = sumArrays(numbers[0], numbers[1]), CD = sumArrays(numbers[2], numbers[3]);
        long count = 0;
        int i = 0, j = n * n - 1;
        while (i < n * n && j >= 0) {
            int sum = AB[i] + CD[j];
            if (sum == 0) {
                long ABDup = 0, CDDup = 0;
                int tempI = i, tempJ = j;
                while (i < n * n && AB[i] == AB[tempI]) {
                    i++;
                    ABDup++;
                }
                while (j >= 0 && CD[j] == CD[tempJ]) {
                    j--;
                    CDDup++;
                }
                count += ABDup * CDDup;
            } else if (sum > 0) j--;
            else i++;
        }
        System.out.println(count);
    }
    private static int[] sumArrays(int[] array1, int[] array2) {
        int[] sums = new int[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sums[index++] = array1[i] + array2[j];
            }
        }
        Arrays.sort(sums);
        return sums;
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