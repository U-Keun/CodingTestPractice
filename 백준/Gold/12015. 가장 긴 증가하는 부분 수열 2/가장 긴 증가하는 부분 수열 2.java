import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int[] input = new int[N];
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        ArrayList<Integer> sequence = new ArrayList<>();
        sequence.add(input[0]);
        for (int i = 1; i < N; i++) {
            if (input[i] > sequence.get(sequence.size() - 1)) sequence.add(input[i]);
            else {
                int idx = Collections.binarySearch(sequence, input[i]);
                if (idx >= 0) sequence.set(idx, input[i]);
                else sequence.set(- idx - 1, input[i]);
            }
        }
        bw.write(String.valueOf(sequence.size()));
        bw.flush();
        bw.close();
    }
}