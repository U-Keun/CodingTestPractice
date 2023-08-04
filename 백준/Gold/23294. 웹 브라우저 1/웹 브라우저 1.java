import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder print = new StringBuilder();
    static int N, Q, C, currentPage = 0, cacheInUse = 0;
    static int[] caches;
    static Deque<Integer> backward = new LinkedList<>(), forward = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        setVariables();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "B":
                    if (!backward.isEmpty()) {
                        forward.push(currentPage);
                        currentPage = backward.pollLast();
                    }
                    break;
                case "F":
                    if (!forward.isEmpty()) {
                        backward.add(currentPage);
                        currentPage = forward.pollFirst();
                    }
                    break;
                case "A":
                    int k = Integer.parseInt(st.nextToken());
                    while (!forward.isEmpty()) {
                        cacheInUse -= caches[forward.poll() - 1];
                    }
                    if (currentPage != 0) backward.add(currentPage);
                    currentPage = k;
                    cacheInUse += caches[k - 1];
                    while (!backward.isEmpty() && cacheInUse > C) {
                        cacheInUse -= caches[backward.pollFirst() - 1];
                    }
                    break;
                case "C":
                    int prev = 0;
                    int l = backward.size();
                    for (int j = 0; j < l; j++) {
                        int m = backward.pollLast();
                        if (prev != m) {
                            backward.push(m);
                            prev = m;
                        }
                        else cacheInUse -= caches[m - 1];
                    }
                    break;
            }
        }
        print.append(currentPage).append('\n');
        if (!backward.isEmpty()) {
            int l = backward.size();
            for (int i = 0; i < l; i++) {
                print.append(backward.pollLast()).append(" ");
            }
        } else print.append(-1);
        print.append('\n');
        if (!forward.isEmpty()) {
            int l = forward.size();
            for (int i = 0; i < l; i++) {
                print.append(forward.pollFirst()).append(" ");
            }
        } else print.append(-1);
        System.out.println(print);
    }
    static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        caches = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            caches[i] = Integer.parseInt(st.nextToken());
        }
    }
}