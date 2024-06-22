import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder print = new StringBuilder();
        
        while (t-- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> record = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int k = Integer.parseInt(br.readLine());
                updateRecord(record, k);
            }
            print.append(record.size());
            if (t > 0) print.append("\n");
        }
        
        br.close();
        System.out.println(print);
    }
    private static void updateRecord(List<Integer> record, int number) {
        int low = 0, high = record.size();

        while (low < high) {
            int mid = (low + high) >> 1;
            if (record.get(mid) < number) low = mid + 1;
            else high = mid;
        }

        if (record.size() == low) record.add(number);
        else record.set(low, number);
    }
}