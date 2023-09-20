import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Line l1 = Line.create(new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())},
                    new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())});
            st = new StringTokenizer(br.readLine());
            Line l2 = Line.create(new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())},
                    new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())});
            if (l1.isIntersect(l2)) bw.write(String.valueOf(1));
            else bw.write(String.valueOf(0));
            bw.flush();
        }
    }
}
class Line {
    private final long[] p1, p2;
    private Line(long[] p1, long[] p2) {
        if (p1[0] > p2[0]) {
            this.p1 = p2;
            this.p2 = p1;
        } else if (p1[0] == p2[0]) {
            if (p1[1] > p2[1]) {
                this.p1 = p2;
                this.p2 = p1;
            } else {
                this.p1 = p1;
                this.p2 = p2;
            }
        } else {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    public static Line create(long[] p1, long[] p2) {
        return new Line(p1, p2);
    }
    public long[] getP1() { return p1; }
    public long[] getP2() { return p2; }
    public int ccw(long[] p1, long[] p2, long[] p3) {
        long s = p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1]
                - p1[1] * p2[0] - p2[1] * p3[0] - p3[1] * p1[0];
        if (s > 0) return 1;
        else if (s == 0) return 0;
        return -1;
    }
    public boolean isIntersect(Line other) {
        long[] p1 = this.getP1(), p2 = this.getP2(), p3 = other.getP1(), p4 = other.getP2();
        int p1p2 = ccw(p1, p2, p3) * ccw(p1, p2, p4), p3p4 = ccw(p3, p4, p1) * ccw(p3, p4, p2);
        if (p1p2 == 0 && p3p4 == 0) {
            if (p2[0] == p3[0] && p2[1] == p3[1]) return true;
            return p3[0] <= p2[0] && p1[0] <= p4[0] && p3[1] <= p2[1] && p1[1] <= p4[1];
        }
        return p1p2 <= 0 && p3p4 <= 0;
    }
}