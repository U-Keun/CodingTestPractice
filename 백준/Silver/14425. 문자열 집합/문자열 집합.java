import java.io.*;
import java.util.HashSet;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
        HashSet<String> strings = new HashSet<>();
        for (int i = 0; i < N; i++) {
            strings.add(br.readLine());
        }
        int count = 0;
        for (int i = 0; i < M; i++) {
            String test = br.readLine();
            if (strings.contains(test)) count++;
        }
        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}