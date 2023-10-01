class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = calc(arr, queries[i]);
        }
        return answer;
    }
    private int calc(int[] arr, int[] query) {
        int min = Integer.MAX_VALUE;
        for (int j = query[0]; j <= query[1]; j++) {
            if (arr[j] > query[2]) min = Math.min(min, arr[j]);
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return min;
    }
}