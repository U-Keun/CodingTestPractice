import java.awt.Point;
import java.io.IOException;

public class Main {
    private static class Circle {
        Point center;
        int radius;
        Circle(int x, int y, int radius) {
            this.center = new Point(x, y);
            this.radius =radius;
        }
        boolean containsPoint(Point point) {
            return center.distance(point) <= radius;
        }
    }
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < T; i++) {
            Point rose = new Point(readInt(), readInt());
            Point prince = new Point(readInt(), readInt());
            int n = readInt(), count = 0;
            for (int j = 0; j < n; j++) {
                Circle circle = new Circle(readInt(), readInt(), readInt());
                if (isDivided(circle, rose, prince)) count++;
            }
            print.append(String.format("%d\n", count));
        }
        System.out.println(print);
    }
    private static boolean isDivided(Circle circle, Point rose, Point prince) {
        return (circle.containsPoint(rose) && !circle.containsPoint(prince))
                || (circle.containsPoint(prince) && !circle.containsPoint(rose));
    }
    private static int readInt() throws IOException {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}