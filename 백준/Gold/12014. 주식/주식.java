import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            print.append("Case #").append(i).append("\n");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            int[] stocks = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer> record = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                record = updateRecord(record, stocks[j]);
            }

            if (record.size() >= K) print.append(1);
            else print.append(0);
            if (i != T) print.append("\n");
        }
        System.out.println(print);
    }

    private static List<Integer> updateRecord(List<Integer> record, int target) {
        int low = 0, high = record.size();
        while (low < high) {
            int mid = (low + high) >> 1;

            if (record.get(mid) < target) low = mid + 1;
            else high = mid;
        }

        if (record.size() == low) record.add(target);
        else record.set(low, target);

        return record;
    }

}