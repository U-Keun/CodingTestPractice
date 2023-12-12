import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> counts = new ArrayList<>();
            char[] input = br.readLine().toCharArray();
            char prev = input[0];
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (prev == input[i]) {
                    count++;
                } else {
                    counts.add(count);
                    count = 1;
                    prev = input[i];
                }
            }
            int odd = 0, even = 0;
            for (int i = 0; i < counts.size(); i++) {
                if (i % 2 == 0) {
                    odd += counts.get(i);
                } else even += counts.get(i);
            }
            bw.write(String.valueOf(Math.min(odd, even)));
            bw.flush();
        }
    }
}