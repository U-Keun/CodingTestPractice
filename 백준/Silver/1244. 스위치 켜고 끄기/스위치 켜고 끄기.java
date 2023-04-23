import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int[] switches = new int[N];
        for (int i = 0; i < N; i++){
            switches[i] = Integer.parseInt(st1.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st2;
        for (int i = 0; i < M; i++) {
            st2 = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st2.nextToken()) == 1) {
                man(switches, Integer.parseInt(st2.nextToken()));
            } else woman(switches, Integer.parseInt(st2.nextToken()));
        }
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < N; i++) {
            print.append(switches[i] + " ");
            if (i % 20 == 19) print.append('\n');
        }
        System.out.println(print);
    }
    public static void man(int[] arr, int k) {
        int multiple = k;
        while (multiple <= arr.length) {
            if (arr[multiple - 1] == 1) arr[multiple - 1] = 0;
            else arr[multiple - 1] = 1;
            multiple += k;
        }
    }
    public static void woman(int[] arr, int k) {
        int halfRange = Math.min(k - 1, arr.length - k);
        if (arr[k - 1] == 1) arr[k - 1] = 0;
        else arr[k - 1] = 1;
        for (int i = 0; i < halfRange; i++) {
            if (arr[k + i] == arr[k - i - 2]) {
                if (arr[k + i] == 1) {
                    arr[k + i] = 0;
                    arr[k - i - 2] = 0;
                } else {
                    arr[k + i] = 1;
                    arr[k - i - 2] = 1;
                }
            } else break;
        }
    }
}