import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();
        List<Integer> lis = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int index = binarySearch(lis, numbers[i]);
            if (lis.size() <= index) {
                lis.add(numbers[i]);
            } else {
                lis.set(index, numbers[i]);
            }
        }
        System.out.println(lis.size());
    }
    private static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}