import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] switches = readIntegers(br.readLine());
        int[] bulbs = readIntegers(br.readLine());
        br.close();

        int[] permutation = getPermutation(switches, bulbs, N);

        List<Integer> lis = new ArrayList<>();
        int[] record = new int[N];
        for (int i = 0; i < N; i++) {
            int index = binarySearch(lis, permutation[i]);
            record[i] = index;
            if (lis.size() == index) {
                lis.add(permutation[i]);
            } else {
                lis.set(index, permutation[i]);
            }
        }

        int l = lis.size() - 1;
        PriorityQueue<Integer> sort = new PriorityQueue<>();
        for (int i = N - 1; i >= 0; i--) {
            if (record[i] != l) continue;
            sort.add(switches[i]);
            l--;
        }

        StringBuilder print = new StringBuilder();
        print.append(lis.size()).append("\n");
        while (!sort.isEmpty()) {
            print.append(sort.poll()).append(" ");
        }

        System.out.println(print);
    }

    private static int[] readIntegers(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] getPermutation(int[] switches, int[] bulbs, int N) {
        int[] permutation = new int[N];
        for (int i = 0; i < N; i++) {
            int index = 0;
            while (bulbs[index] != switches[i]) index++;
            permutation[i] = index;
        }
        return permutation;
    }

    private static int binarySearch(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) >> 1;
            if (list.get(mid) < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}