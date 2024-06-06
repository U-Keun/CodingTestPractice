import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder print = new StringBuilder();
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());

            int[] numbers = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> lis = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int index = binarySearch(lis, numbers[i]);
                if (lis.size() == index) {
                    lis.add(numbers[i]);
                } else {
                    lis.set(index, numbers[i]);
                }
            }
            print.append(lis.size()).append("\n");
        }
        br.close();
        System.out.println(print);
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