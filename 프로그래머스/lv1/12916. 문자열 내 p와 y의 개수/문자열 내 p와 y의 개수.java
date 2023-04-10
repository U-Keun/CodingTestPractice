class Solution {
    boolean solution(String s) {
        boolean answer = true;
        String[] alphabets = s.split("");
        int ps = 0;
        int ys = 0;
        for (String str:alphabets) {
            if (str.toLowerCase().equals("p")) ps++;
            else if (str.toLowerCase().equals("y")) ys++;
        }
        if (ps != ys) answer = false;
        return answer;
    }
}