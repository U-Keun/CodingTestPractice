class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            if (Math.sqrt((double)i) == (int)Math.sqrt((double) i)) {
                answer -= i;
            } else answer += i;
        }
        return answer;
    }
}