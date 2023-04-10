import static java.lang.Character.isDigit;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int len = s.length();
        if (len == 4 || len == 6) {
            for (int i = 0; i < len; i++) {
                if (!isDigit(s.charAt(i))) {
                    answer = false;
                    break;
                }
            }
        } else answer = false;
        return answer;
    }
}