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

        List<Integer> record = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            record = updateRecord(record, numbers[i]);
        }

        System.out.println(N - record.size());
    }

    private static List<Integer> updateRecord(List<Integer> array, int target) {
        int low = 0, high = array.size();
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array.get(mid) < target) low = mid + 1;
            else high = mid;
        }

        if (low == array.size()) array.add(target);
        else array.set(low, target);

        return array;
    }
}