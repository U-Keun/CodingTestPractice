import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            List<Integer> indices = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) continue;
                indices.add(j + 1);
            }

            if (indices.size() % 2 != 0) print.append(-1);
            else {
                if (indices.isEmpty()) print.append(1);
                else print.append(indices.get(indices.size() / 2 - 1));
            }
            print.append("\n");
        }
        System.out.println(print);
    }
}