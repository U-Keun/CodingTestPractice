import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    public int x;
    public int y;

    public Point(String[] info) {
        this.x = Integer.parseInt(info[0]);
        this.y = Integer.parseInt(info[1]);
    }

    static int getX(Point point) {
        return point.x;
    }

    static int getY(Point point) {
        return point.y;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Point> pointList = new ArrayList<>();
        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            pointList.add(new Point(input));
        }

        pointList.sort(Comparator.comparing(Point::getX).thenComparing(Point::getY));
        for (Point point:pointList) {
            print.append(point.x + " " + point.y).append('\n');
        }
        System.out.println(print);
    }
}