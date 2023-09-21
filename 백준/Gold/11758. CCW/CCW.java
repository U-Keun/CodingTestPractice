import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long[] p1, p2, p3;
            StringTokenizer st = new StringTokenizer(br.readLine());
            p1 = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            p2 = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            p3 = new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
            bw.write(String.valueOf(ccw(p1, p2, p3)));
            bw.flush();
        }
    }
    public static int ccw(long[] p1, long[] p2, long[] p3) {
        long s = (p2[0] - p1[0]) * (p3[1] - p1[1]) - (p3[0] - p1[0]) * (p2[1] - p1[1]);
        if (s > 0) return 1;
        else if (s == 0) return 0;
        return -1;
    }
}