class Solution {
    public String solution(String s) {
        String answer = "";
        String[] str_s = s.split("");
        int cnt = 0;

        for (int i = 0; i < str_s.length; i++) {
            if (str_s[i].equals(" ")) {
               cnt = 0;
            } else {
                if (cnt % 2 == 0) {
                    str_s[i] = str_s[i].toUpperCase();
                    cnt++;
                } else if (cnt % 2 == 1) {
                    str_s[i] = str_s[i].toLowerCase();
                    cnt++;
                }
            }
            answer = answer + str_s[i];
        }

        return answer;
    }
}