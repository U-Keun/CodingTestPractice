import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for (int i: d) {
            budget -= i;
            answer++;
            if (budget < 0) {
                answer--;
                break;
            }
        }
        return answer;
    }
}