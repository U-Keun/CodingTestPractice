class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int b = Math.max(n,m);
        int s = Math.min(n,m);
        int tmp;
        while (b % s != 0) {
            tmp = b % s;
            b = s;
            s = tmp;
        }
        answer[0] = s;
        answer[1] = n * m / s;
        return answer;
    }
}