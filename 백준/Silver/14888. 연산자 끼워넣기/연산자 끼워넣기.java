import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> A = new LinkedList<>();
        for (int i = 0; i < N; i++) A.offer(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        br.close();
        int[] operations = new int[4];
        for (int i = 0; i < 4; i++) operations[i] = Integer.parseInt(st.nextToken());
        recursion(A, operations);
        StringBuilder print = new StringBuilder();
        print.append(max).append('\n').append(min);
        bw.write(print.toString());
        bw.flush();
        bw.close();
    }
    public static void recursion(Deque<Integer> numbers, int[] operations) {
        if (Arrays.stream(operations).sum() == 0) {
            Integer k = numbers.poll();
            min = Math.min(min, k);
            max = Math.max(max, k);
            return;
        }
        Integer a = numbers.poll(), b = numbers.poll();
        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                switch (i) {
                    case 0: numbers.addFirst(a + b); break;
                    case 1: numbers.addFirst(a - b); break;
                    case 2: numbers.addFirst(a * b); break;
                    case 3: numbers.addFirst(a / b); break;
                }
                operations[i] -= 1;
                recursion(numbers, operations);
                operations[i] += 1;
                numbers.poll();
            }
        }
        numbers.addFirst(b);
        numbers.addFirst(a);
    }
}