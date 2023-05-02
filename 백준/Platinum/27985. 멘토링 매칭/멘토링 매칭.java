import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> request = new LinkedList<>();

        int[][] studentPreference = new int[N][N];
        int[][] kaistPreference = new int[N][N];
        int[] matchings = new int[N];
        StringTokenizer st1;
        for (int i = 0; i < N; i++) {
            st1 = new StringTokenizer(br.readLine());
            request.add(i + 1);
            for (int j = 0; j < N; j++) {
                studentPreference[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            int[] row = preferById(N, i + 1);
            for (int j = 0; j < N; j++) {
                kaistPreference[i][j] = row[j];
            }
        }
        while (request.size() != 0) {
            int confession = request.peek();
            int bestOption = 0;
            for (int i:studentPreference[confession - 1]) {
                if (i != 0) {
                    bestOption = i;
                    break;
                }
            }
            if (!hasLover(matchings, bestOption)) {
                matchings[request.poll() - 1] = bestOption; ;
            }
            else {
                int presentBf = getPresentBf(matchings, bestOption);
                for (int j:kaistPreference[bestOption - 1]) {
                    if (j == presentBf) {
                        break;
                    } else if (j == confession) {
                        matchings[presentBf - 1] = 0;
                        matchings[request.poll() - 1] = bestOption;
                        request.add(presentBf);
                        break;
                    }
                }
            }
            matchedOrBrokeUp(studentPreference, confession);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(matchings[i] + " ");
        }
    }
    static boolean hasLover(int[] matchings, int bestOption) {
        for (int i: matchings) {
            if (i == bestOption) {
                return true;
            }
        }
        return false;
    }
    static void matchedOrBrokeUp(int[][] preference, int confession) {
        for (int i = 0; i < preference[confession - 1].length; i++) {
            if (preference[confession - 1][i] != 0) {
                preference[confession - 1][i] = 0;
                break;
            }
        }
    }
    static int getPresentBf(int[] matchings, int k) {
        int presentBf = 0;
        for (int i = 0; i < matchings.length; i++) {
            if (matchings[i] == k) {
                presentBf = i + 1;
            }
        }
        return presentBf;
    }
    static int[] preferById(int N, int i) {
        Map<Integer, Integer> prefer = new HashMap<>();
        int[] answer = new int[N];
        for (int j = 0; j < N; j++) {
            prefer.put(j + 1, Math.abs(j - i + 1));
        }
        List<Integer> studentsList = new ArrayList<>(prefer.keySet());
        Collections.sort(studentsList, (o1, o2) -> (prefer.get(o2).compareTo(prefer.get(o1))));
        for (int j = 0; j < N; j++) {
            answer[j] = studentsList.get(j);
        }
        return answer;
    }
}