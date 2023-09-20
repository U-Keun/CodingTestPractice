import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static HashMap<double[], double[]> parent = new HashMap<>();
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            double[][] points = new double[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                double[] point = new double[2];
                point[0] = Double.parseDouble(st.nextToken());
                point[1] = Double.parseDouble(st.nextToken());
                points[i] = point;
                parent.put(point, point);
            }
            PriorityQueue<Edge> edges = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    edges.add(Edge.create(points[i], points[j]));
                }
            }
            double answer = 0;
            while (n > 1) {
                Edge tmp = edges.poll();
                if (union(tmp.getStar1(), tmp.getStar2())) {
                    answer += tmp.getDistance();
                    n--;
                }
            }
            bw.write(String.valueOf(answer));
            bw.flush();
        }

    }
    public static double[] find(double[] x) {
        if (Arrays.equals(parent.get(x), x)) return x;
        return find(parent.get(x));
    }
    public static boolean union(double[] x, double[] y) {
        x = find(x);
        y = find(y);
        if (Arrays.equals(x, y)) return false;
        if (x[0] < y[0]) parent.replace(y, x);
        else if (x[0] == y[0]) {
            if (x[1] < y[1]) parent.replace(y, x);
            else parent.replace(x, y);
        } else parent.replace(x, y);
        return true;
    }
}
class Edge implements Comparable<Edge> {
    private final double[] star1, star2;
    private final double distance;

    private Edge(double[] star1, double[] star2) {
        this.star1 = star1;
        this.star2 = star2;
        this.distance = Math.sqrt(Math.pow(star1[0] - star2[0], 2) + Math.pow(star1[1] - star2[1], 2));
    }
    public static Edge create(double[] star1, double[] star2) {
        return new Edge(star1, star2);
    }
    public double[] getStar1() { return star1; }
    public double[] getStar2() { return star2; }
    public double getDistance() { return distance; }
    @Override
    public int compareTo(Edge other) {
        double value = this.distance - other.distance;
        if (value > 0) return 1;
        else if (value == 0) return 0;
        else return -1;
    }
}