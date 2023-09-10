import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        boolean[] alphabet;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            alphabet = new boolean[26];
            char[] input = br.readLine().toCharArray();
            int l = input.length;
            boolean isGroupWord = true;
            for (int j = 0; j < l; j++) {
                if (!alphabet[input[j] - 'a']) alphabet[input[j] - 'a'] = true;
                else {
                    isGroupWord = false;
                    break;
                }
                while (j + 1 < l && input[j] == input[j + 1]) j++;
            }
            if (isGroupWord) answer++;
        }
        br.close();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}