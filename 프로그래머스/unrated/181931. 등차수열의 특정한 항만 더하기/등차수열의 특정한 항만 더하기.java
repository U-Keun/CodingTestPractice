class Solution {
    public int solution(int a, int d, boolean[] included) {
        int answer = 0, value = a;
        for (int i = 0; i < included.length; i++) {
            if (included[i]) answer += value;
            value += d;
        }
        return answer;
    }
}