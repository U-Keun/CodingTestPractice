import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, s, e, idx = 0;
    static PriorityQueue<Reservation> reservation = new PriorityQueue<>(Comparator.comparing(a -> a.start));
    static PriorityQueue<Computer> computers = new PriorityQueue<>(Comparator.comparing(a -> a.endTime));
    static PriorityQueue<Integer> available = new PriorityQueue<>();
    static List<Integer> record = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            reservation.add(new Reservation(s, e));
        }
        Reservation user;
        while (!reservation.isEmpty()) {
            user = reservation.poll();
            if (computers.isEmpty()) {
                computers.add(new Computer(idx++, user.end));
                record.add(1);
            } else {
                while (!computers.isEmpty() && computers.peek().endTime < user.start) {
                    available.add(computers.poll().idx);
                }
                if (available.isEmpty()) {
                    computers.add(new Computer(idx++, user.end));
                    record.add(1);
                } else {
                    int k = available.poll();
                    computers.add(new Computer(k, user.end));
                    record.set(k, record.get(k) + 1);
                }
            }
        }
        print.append(record.size()).append('\n');
        record.stream().forEach(a -> print.append(a).append(" "));
        System.out.println(print);
    }
}
class Reservation {
    int start;
    int end;
    public Reservation(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Computer {
    int idx;
    int endTime;
    public Computer(int idx, int endTime) {
        this.idx = idx;
        this.endTime = endTime;
    }
}