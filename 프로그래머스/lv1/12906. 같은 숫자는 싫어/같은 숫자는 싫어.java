import java.util.*;
class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> intList = new ArrayList<>();
        int prev = -1;
        for (int i=0; i< arr.length; i++) { // { 1, 1, 3, 3, 0 }
            if (prev!=arr[i]) { // prev = 1  3
                intList.add(arr[i]); // intList : {1,3,0}
                prev = arr[i];
            }
        }
        int[] answer = new int[intList.size()];
        for (int i = 0; i < intList.size(); i++) {
            answer[i] = intList.get(i);
        }
        // 숙제: ArrayList를 정수 배열로 바꾸는 방법을 구글링해보세요!
        return answer;
    }
}