import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder print = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static Integer[] numbers;
    static Set<Integer> set;
    static ArrayList<Integer> sortedList;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        numbers = new Integer[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        set = new TreeSet<>(Arrays.asList(numbers));
        sortedList = new ArrayList<>(set);
        for (int i:numbers) {
            print.append(bs(sortedList, i)).append(" ");
        }
        System.out.println(print);
    }
    static int bs(ArrayList<Integer> list, int k) {
        int left = 0, right = list.size(), mid;
        do {
            mid = (left + right) / 2;
            if (list.get(mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        } while (left < right);
        if (list.get(mid) == k) return mid;
        else return mid + 1;
    }
}