import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        int answer = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= 'C') answer += 3;
            else if (c <= 'F') answer += 4;
            else if (c <= 'I') answer += 5;
            else if (c <= 'L') answer += 6;
            else if (c <= 'O') answer += 7;
            else if (c <= 'S') answer += 8;
            else if (c <= 'V') answer += 9;
            else if (c <= 'Z') answer += 10;
        }
        br.close();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}