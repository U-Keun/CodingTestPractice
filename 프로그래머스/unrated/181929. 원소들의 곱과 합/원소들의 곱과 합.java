class Solution {
    public int solution(int[] num_list) {
        int answer = 0, product = 1, square = 0;
        for (int i:num_list) {
            product *= i;
            square += i;
        }
        square *= square;
        if (product < square) answer = 1;
        return answer;
    }
}