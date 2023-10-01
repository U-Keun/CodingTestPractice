class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        int answer = 0;
        boolean check = true;
        if (ineq.equals("<")) {
            if (eq.equals("=")) check = n <= m;
            else check = n < m;
        } else {
            if (eq.equals("=")) check = n >= m;
            else check = n > m;
        }
        if (check) answer = 1;
        return answer;
    }
}