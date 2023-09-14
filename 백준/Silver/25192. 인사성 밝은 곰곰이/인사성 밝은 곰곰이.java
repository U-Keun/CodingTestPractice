import java.io.*;
import java.util.HashSet;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), answer = 0;
        HashSet<String> emoti = null;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.equals("ENTER")) {
                if (emoti != null) answer += emoti.size();
                emoti = new HashSet<>();
            } else emoti.add(input);
        }
        answer += emoti.size();
        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}