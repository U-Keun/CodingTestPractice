import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]), C = Integer.parseInt(input[1]);
            List<Integer> houses = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                houses.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(houses);
            int low = 1, high = houses.get(N - 1) - houses.get(0) + 1;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (C <= countHouses(houses, mid)) low = mid + 1;
                else high = mid;
            }
            bw.write(String.valueOf(low - 1));
            bw.flush();
        }
    }
    private static int countHouses(List<Integer> houses, int distance) {
        int pointer = 0, answer = 1, n = houses.size();
        for (int i = 0; i < n; i++) {
            if (houses.get(i) - houses.get(pointer) >= distance) {
                pointer = i;
                answer++;
            }
        }
        return answer;
    }
}