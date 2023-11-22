import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int candies = Integer.parseInt(st.nextToken()), boxCount = Integer.parseInt(st.nextToken());
                Integer[] boxes = new Integer[boxCount];
                int answer = 0;
                for (int j = 0; j < boxCount; j++) {
                    st = new StringTokenizer(br.readLine());
                    boxes[j] = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
                }
                Arrays.sort(boxes, Comparator.reverseOrder());
                for (Integer capacity : boxes) {
                    if (candies <= 0) break;
                    candies -= capacity;
                    answer++;
                }
                print.append(String.format("%d\n",answer));
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}