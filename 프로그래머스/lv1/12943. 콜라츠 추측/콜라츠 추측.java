class Solution {
    public int solution(int num) {
        int cnt = 0;
        long number = num;
        while (number!=1 && cnt<500) { // num != 1, cnt = 500
            cnt++;
            if (number%2==0) {
                number/=2;
            } else {
                number=number*3+1;
            }
        } // num != 1, cnt = 500
        if (cnt>=500) {
            return -1;
        }
        return cnt;
    }
}