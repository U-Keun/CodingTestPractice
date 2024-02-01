import static java.util.Collections.swap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, F;
    private static List<Integer> permutation;
    private static Queue<Integer> pascalCoefficients = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        permutation = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            permutation.add(i + 1);
            updateCoefficients();
        }
        do {
            int value = sumValues();
            if (value == F) {
                printResult(permutation);
                return;
            }
        } while (hasNextPermutation());

    }
    private static void printResult(List<Integer> permutation) {
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
        while (i > 0 && permutation.get(i) <= permutation.get(i - 1)) i--;
        if (i == 0) return false;

        int j = N - 1;
        while (permutation.get(i - 1) >= permutation.get(j)) j--;
        swap(permutation, i - 1, j);

        j = N -1;
        while(i < j) {
            swap(permutation, i++, j--);
        }

        return true;
    }
    private static int sumValues() {
        int value = 0;
        for (int i = 0; i < N; i++) {
            value += permutation.get(i) * pascalCoefficients.peek();
            pascalCoefficients.add(pascalCoefficients.poll());
        }
        return value;
    }
}