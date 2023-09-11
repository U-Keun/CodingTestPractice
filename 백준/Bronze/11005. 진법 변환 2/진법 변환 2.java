import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Long> unit = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        int number = Integer.parseInt(st.nextToken()), base = Integer.parseInt(st.nextToken()), digit = 1;
        long tmp = 1;
        while (number >= tmp) {
            unit.add(tmp);
            tmp = (long) Math.pow(base, digit++);
        }
        for (int i = digit - 2; i >= 0; i--) {
            long k = number / unit.get(i);
            char c;
            if (k >= 10) c = (char) ('A' + (k - 10));
            else c = (char) ('0' + k);
            bw.write(c + "");
            number %= unit.get(i);
        }
        bw.flush();
        bw.close();
    }
}