import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] S = br.readLine().toCharArray();
            int[][] count = new int[26][S.length + 1];
            for (int i = 1; i <= S.length; i++) {
                for (int j = 0; j < 26; j++) {
                    count[j][i] = count[j][i - 1];
                }
                count[(int) S[i - 1] - 'a'][i]++;
            }
            int q = Integer.parseInt(br.readLine());
            StringTokenizer st;
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
                print.append(count[(int) c - 'a'][r + 1] - count[(int) c - 'a'][Math.max(0, l)]).append('\n');
            }
            bw.write(print.toString());
            bw.flush();
        }
    }
}