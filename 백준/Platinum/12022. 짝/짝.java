import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> request = new LinkedList<>();

        int[][] manPreference = new int[N][N];
        int[][] womanPreference = new int[N][N];
        int[] matchings = new int[N];
        StringTokenizer st1;
        for (int i = 0; i < N; i++) {
            st1 = new StringTokenizer(br.readLine());
            request.add(i + 1);
            for (int j = 0; j < N; j++) {
                manPreference[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        StringTokenizer st2;
        for (int i = 0; i < N; i++) {
            st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                womanPreference[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        while (request.size() != 0) {
            int confession = request.peek(); // 이번에 고백할 남자
            // 고백할 남자가 현재 가장 좋아하는 여자
            int bestOption = 0;
            for (int i:manPreference[confession - 1]) {
                if (i != 0) {
                    bestOption = i;
                    break;
                }
            }
            // 싱글이었던 두 남녀가 매칭되는 경우
            if (!hasLover(matchings, bestOption)) {
                matchings[request.poll() - 1] = bestOption; ;
            } // 고백을 받은 여자가 싱글이 아닌 경우
            else {
                // 고백을 받은 여자의 현재 남자친구
                int presentBf = getPresentBf(matchings, bestOption);
                // 고백을 받은 여자가 기존의 남자보다 방금 고백받은 남자가 더 좋은 경우
                for (int j:womanPreference[bestOption - 1]) {
                    if (j == presentBf) {
                        break;
                    } else if (j == confession) {
                        matchings[presentBf - 1] = 0; // 현재 남자친구와 헤어지고,
                        matchings[request.poll() - 1] = bestOption; // 고백을 받아들임.
                        request.add(presentBf); // 헤어진 남자는 싱글이 됨.
                        break;
                    }
                }
            }
            matchedOrBrokeUp(manPreference, confession);
        }
        for (int i = 0; i < N; i++) {
            System.out.println(matchings[i]);
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
}