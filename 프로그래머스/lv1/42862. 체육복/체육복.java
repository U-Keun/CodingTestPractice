class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] clothes = new int[n];
        for (int i = 0; i < n; i++) {
            clothes[i] = 1;
        }
        for (int j:lost) clothes[j-1]--;
        for (int k:reserve) clothes[k-1]++;
        for (int i = 0; i < n; i++) {
            if (i == 0 && clothes[i] == 2) {
                if (clothes[i+1] == 0) {
                    clothes[i]--;
                    clothes[i+1]++;
                }
            } else if (i == n-1 && clothes[i] == 2) {
                if (clothes[i-1] == 0) {
                    clothes[i]--;
                    clothes[i-1]++;
                }
            } else if (clothes[i] == 2) {
                if (clothes[i-1] == 0) {
                    clothes[i]--;
                    clothes[i-1]++;
                } else if (clothes[i+1] == 0) {
                    clothes[i]--;
                    clothes[i+1]++;
                }
            }
        }
        for (int j:clothes) {
            if (j != 0) answer++;
        }
        return answer;
    }
}