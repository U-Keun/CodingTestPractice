class Solution {
    public int[] solution(int start_num, int end_num) {
        int[] answer = new int[end_num - start_num + 1];
        answer[0] = start_num;
        for (int i = 1; i < end_num - start_num + 1; i++) {
            answer[i] = answer[i - 1] + 1;
        }
        return answer;
    }
}