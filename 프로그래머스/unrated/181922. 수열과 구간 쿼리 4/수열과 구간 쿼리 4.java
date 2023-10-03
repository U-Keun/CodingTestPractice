class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            for (int j = query[0]; j <= query[1]; j++) {
                if (j % query[2] == 0) arr[j]++;
            }
        }
        return arr;
    }
}