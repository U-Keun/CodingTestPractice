class Solution {
    public int solution(String s) {
        // 앞에가 +인지 -인지 판별
        // 스트링으로 써있는 숫자를 int 형으로 바꿔야 해요. ex. "-1234"
        int answer = 0;
        if (s.substring(0,1)=="-") {
            answer = -1 * Integer.parseInt(s.substring(1));
        } else {
            answer = Integer.parseInt(s); // Integer.valueOf(s)
        }
        return answer;
    }
}