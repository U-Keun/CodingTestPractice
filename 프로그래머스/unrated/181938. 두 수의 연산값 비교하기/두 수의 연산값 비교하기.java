class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int ab = Integer.parseInt(String.valueOf(a) + String.valueOf(b));
        answer = Math.max(ab, 2 * a * b);
        return answer;
    }
}