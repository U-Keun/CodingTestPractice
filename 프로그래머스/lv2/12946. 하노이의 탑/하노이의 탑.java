class Solution {
    public int[][] solution(int n) {
        return dfs(n, 1, 3);
    }
    public int[][] dfs(int n, int from, int to) {
        int[][] answer;
        if (n == 1) {
            answer = new int[1][2];
            answer[0] = new int[]{from, to};
            return answer;
        }
        int[][] prev = dfs(n - 1, from, 6 - from - to);
        int[][] next = dfs(n - 1, 6 - from - to, to);
        int k = prev.length;
        answer = new int[2 * k + 1][2];
        for (int i = 0; i < 2 * k + 1; i++) {
            if (i == k) answer[i] = new int[]{from, to};
            else if (i < k) answer[i] = prev[i];
            else answer[i] = next[i - k - 1];
        }
        return answer;
    }
}