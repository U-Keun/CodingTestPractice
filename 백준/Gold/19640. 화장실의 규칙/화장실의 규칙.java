import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K;
    static Queue<Employee>[] rows;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rows = new Queue[M];
        boolean isDeka;
        for (int i = 0; i < N; i++) {
            isDeka = (i == K);
            st = new StringTokenizer(br.readLine());
            if (rows[i % M] == null) rows[i % M] = new LinkedList<>();
            rows[i % M].add(new Employee(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), isDeka));
        }
        int count = 0;
        Employee nextUser;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            if (rows[i] != null && !rows[i].isEmpty()) {
                pq.offer(new Pair(i, rows[i].peek()));
            }
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            nextUser = pair.employee;
            int rowIdx = pair.rowIdx;
            if (nextUser.isDeka) break;

            rows[rowIdx].poll();
            if (!rows[rowIdx].isEmpty()) {
                pq.offer(new Pair(rowIdx, rows[rowIdx].peek()));
            }
            count++;
        }
        System.out.println(count);
    }
    static class Pair implements Comparable<Pair> {
        int rowIdx;
        Employee employee;
        public Pair(int rowIdx, Employee employee) {
            this.rowIdx = rowIdx;
            this.employee = employee;
        }
        @Override
        public int compareTo(Pair pair) {
            if (this.employee.compareTo(pair.employee) == 0) return Integer.compare(this.rowIdx, pair.rowIdx);
            return this.employee.compareTo(pair.employee);
        }
    }
}
class Employee implements Comparable<Employee> {
    int workingDays;
    int emergency;
    boolean isDeka;
    public Employee(int workingDays, int emergency, boolean isDeka) {
        this.workingDays = workingDays;
        this.emergency = emergency;
        this.isDeka = isDeka;
    }
    @Override
    public int compareTo(Employee employee) {
        if (this.workingDays == employee.workingDays) return Integer.compare(employee.emergency, this.emergency);
        return Integer.compare(employee.workingDays, this.workingDays);
    }
}