class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] input = s.split(" ");
        int prev = 0;
        for (String str : input) {
            if (str.equals("Z")) {
                answer -= prev;
            } else {
                prev = Integer.parseInt(str);
                answer += prev;
            }
        }
        return answer;
    }
}