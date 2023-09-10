import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        char[][] array = new char[5][15];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            char[] input = br.readLine().toCharArray();
            int l = input.length;
            for (int j = 0; j < l; j++) {
                array[i][j] = input[j];
            }
            k = Math.max(k, l);
        }
        br.close();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[j][i] == '\0') continue;
                bw.write("" + array[j][i]);
            }
        }
        bw.flush();
        bw.close();
    }
}