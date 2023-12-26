import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    private static class Problem {
        int index;
        int difficulty;
        boolean removed;
        Problem(int index, int difficulty) {
            this.index = index;
            this.difficulty = difficulty;
        }
        void solve() {
            removed = true;
        }
    }
    private static PriorityQueue<Problem> problemsAsc = new PriorityQueue<>((o1, o2) -> {
        if (o1.difficulty == o2.difficulty) {
            return o1.index - o2.index;
        }
        return o1.difficulty - o2.difficulty;
    });
    private static PriorityQueue<Problem> problemsDes = new PriorityQueue<>((o1, o2) -> {
        if (o1.difficulty == o2.difficulty) {
            return o2.index - o1.index;
        }
        return o2.difficulty - o1.difficulty;
    });
    private static Map<Integer, Problem> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                int P = Integer.parseInt(input[0]), L = Integer.parseInt(input[1]);
                addProblem(new Problem(P, L));
            }
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                String[] input = br.readLine().split(" ");
                switch (input[0]) {
                    case "add" :
                        int P = Integer.parseInt(input[1]), L = Integer.parseInt(input[2]);
                        addProblem(new Problem(P, L));
                        break;
                    case "recommend" :
                        bw.write(String.format("%d\n", recommendProblem(input[1])));
                        break;
                    case "solved" :
                        removeProblem(input[1]);
                        break;
                }
            }
            bw.flush();
        }
    }
    private static void addProblem(Problem problem) {
        problemsDes.add(problem);
        problemsAsc.add(problem);
        map.put(problem.index, problem);
    }
    private static int recommendProblem(String type) {
        if (type.equals("1")) {
            while (problemsDes.peek().removed) {
                problemsDes.poll();
            }
            return problemsDes.peek().index;
        }
        while (problemsAsc.peek().removed) {
            problemsAsc.poll();
        }
        return problemsAsc.peek().index;
    }
    private static void removeProblem(String index) {
        int indexNumber = Integer.parseInt(index);
        map.get(indexNumber).solve();
    }
}