import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] index = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            index[Integer.parseInt(st.nextToken()) - 1] = i;
        }
        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = index[Integer.parseInt(st.nextToken()) - 1];
        }
        br.close();

        List<Integer> lis = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int size = lis.size();
            if (size == 0 || lis.get(size - 1) < array[i]) {
                lis.add(array[i]);
            } else {
                int idx = binarySearch(lis, array[i]);
                if (idx < size) {
                    lis.set(idx, array[i]);
                } else lis.add(array[i]);
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