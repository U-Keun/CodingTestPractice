import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] cleared = new int[N];
        int[] reachedPlayers = new int[N];
        Map<Integer, Double> failedRate = new HashMap<>();
        for (int i = 0; i < stages.length; i++) {
            if (stages[i] == N + 1) {
                for (int j = 0; j < N; j++) {
                    reachedPlayers[j]++;
                    cleared[j]++;
                }
            } else {
                for (int j = 0; j < stages[i]; j++) {
                    reachedPlayers[j]++;
                    if (j != stages[i] - 1) cleared[j]++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (reachedPlayers[i] == 0) {
                failedRate.put(i+1,0.0);
            } else {
                failedRate.put(i+1, 1-(double)cleared[i]/reachedPlayers[i]);
            }

        }
        List<Map.Entry<Integer, Double>> entryList = new LinkedList<>(failedRate.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        System.out.println(entryList);
        int idx = 0;
        for(Map.Entry<Integer, Double> entry : entryList){
            answer[idx] = entry.getKey();
            idx++;
        }
        return answer;
    }
}