class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        int n = my_string.length();
        for (int i = 0; i < n; i++) {
            if (i == s) {
                answer += overwrite_string;
                i += overwrite_string.length() - 1;
                continue;
            }
            answer += my_string.charAt(i);
        }
        return answer;
    }
}