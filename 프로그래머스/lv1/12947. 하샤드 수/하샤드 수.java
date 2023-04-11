class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int number = 0;
        int n = x;

        while (x!=0) {
            number += x % 10;
            x = x / 10;
        }
        if (n%number==0) {
            return answer;
        } else {
            answer = false;
            return answer;
        }

    }
}