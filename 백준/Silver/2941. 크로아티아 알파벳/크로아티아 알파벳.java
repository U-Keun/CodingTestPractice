import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        char[] input = br.readLine().toCharArray();
        br.close();
        int answer = 0, l = input.length;
        for (int i = 0; i < l; i++) {
            answer++;
            if (input[i] == 'c' && i + 1 < l && (input[i + 1] == '-' || input[i + 1] == '=')) i++;
            else if (input[i] == 'd') {
                if (i + 2 < l && input[i + 1] == 'z' && input[i + 2] == '=') i += 2;
                else if (i + 1 < l && input[i + 1] == '-') i++;
            } else if (input[i] == 'l' && i + 1 < l && input[i + 1] == 'j') i++;
            else if (input[i] == 'n' && i + 1 < l && input[i + 1] == 'j') i++;
            else if (input[i] == 's' && i + 1 < l && input[i + 1] == '=') i++;
            else if (input[i] == 'z' && i + 1 < l && input[i + 1] == '=') i++;
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}