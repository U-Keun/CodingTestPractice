import java.io.*;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), answer = 0;
        HashMap<String, Boolean> doTheyDance = new HashMap<>();
        doTheyDance.put("ChongChong", true);
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            doTheyDance.putIfAbsent(input[0], false);
            doTheyDance.putIfAbsent(input[1], false);
            if (doTheyDance.get(input[0]) || doTheyDance.get(input[1])) {
                doTheyDance.replace(input[0], true);
                doTheyDance.replace(input[1], true);
            }
        }
        for (Boolean tf:doTheyDance.values()) if (tf) answer++;
        br.close();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}