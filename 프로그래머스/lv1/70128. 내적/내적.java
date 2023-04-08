class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0; // a = { 1,2,3,4}, b = {4,3,2,1}

        for (int i = 0; i<a.length; i++) {
            answer += a[i]*b[i];
        }

        return answer;
    }
}