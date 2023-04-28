import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder print = new StringBuilder();
        StringTokenizer st;
        int N, M;
        Map<Integer, Integer> record = new HashMap<>();
        do {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            if (N == 0 && M == 0) break;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int k = Integer.parseInt(st.nextToken());
                    if (record.containsKey(k)) {
                        record.compute(k, (l, integer) -> integer + 1);
                    } else {
                        record.put(k, 1);
                    }
                }
            }
            List<Integer> ranks = record.values().stream().distinct().collect(toList());
            Map<Integer, List<Integer>> group = ranks.stream()
                            .collect(toMap(Function.identity(), rank -> record.entrySet().stream()
                                    .filter(entry -> Objects.equals(rank, entry.getValue()))
                                    .map(Map.Entry::getKey)
                                    .collect(toList())));
            int first = 0;
            int second = 0;
            for (Integer k: group.keySet()) {
                if (k > first) {
                    second = first;
                    first = k;
                }
                else if (k > second) {
                    second = k;
                }
            }
            List<Integer> answer = group.get(second);
            Collections.sort(answer);
            for (Integer i:answer) {
                print.append(i + " ");
            }
            print.append('\n');
            record.clear();
        } while (true);
        System.out.println(print);
    }
}