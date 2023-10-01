class Solution {
    public int[] solution(int[] num_list) {
        int n = num_list.length;
        int[] answer = new int[n + 1];
        for (int i = 0; i < n; i++) {
            answer[i] = num_list[i];
        }
        if (answer[n - 2] < answer[n - 1]) answer[n] = answer[n - 1] - answer[n - 2];
        else answer[n] = answer[n - 1] * 2;
        return answer;
    }
}