class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = arr;
        for (int[] query:queries) {
            int tmp = answer[query[0]];
            answer[query[0]] = answer[query[1]];
            answer[query[1]] = tmp;
        }
        return answer;
    }
}