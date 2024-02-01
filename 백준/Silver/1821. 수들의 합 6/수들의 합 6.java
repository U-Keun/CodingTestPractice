import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, F;
    private static int[] permutation;
    private static Queue<Integer> pascalCoefficients = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        permutation = new int[N];
        for (int i = 0; i < N; i++) {
            permutation[i] = i + 1;
            updateCoefficients();
        }
        do {
            int value = 0;
            for (int i = 0; i < N; i++) {
                value += permutation[i] * pascalCoefficients.peek();
                pascalCoefficients.add(pascalCoefficients.poll());
            }
            if (value == F) {
                printResult(permutation);
                return;
            }
        } while (hasNextPermutation());

    }
    private static void printResult(int[] permutation) {
        StringBuilder print = new StringBuilder();
        for (int number : permutation) {
            print.append(number).append(" ");
        }
        System.out.println(print);
    }
    private static void updateCoefficients() {
        int l = pascalCoefficients.size();
        int value = 0;
        for (int i = 0; i < l; i++) {
            pascalCoefficients.add(value + pascalCoefficients.peek());
            value = pascalCoefficients.poll();
        }
        pascalCoefficients.add(1);
    }
    private static boolean hasNextPermutation() {
        int i = N - 1;
        while (i > 0 && permutation[i] <= permutation[i - 1]) i--;
        if (i == 0) return false;

        int j = N - 1;
        while (permutation[i - 1] >= permutation[j]) j--;

        int temp = permutation[j];
        permutation[j] = permutation[i-1];
        permutation[i-1] = temp;

        j = permutation.length-1;
        while(i<j) {
            temp = permutation[j];
            permutation[j] = permutation[i];
            permutation[i] = temp;
            i++;
            j--;
        }

        return true;
    }
}