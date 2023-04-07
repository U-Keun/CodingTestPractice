class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i <= Math.sqrt((double)n); i++) {
            if(n % i == 0) {
                if(i == n / i) {
                    answer += i;
                } else {
                    answer += i;
                    answer += n / i;
                }
                
            } 
        }
        return answer;
    }
}