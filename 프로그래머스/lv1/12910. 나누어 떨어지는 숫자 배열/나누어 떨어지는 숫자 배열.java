import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        // arr = { 9, 7 }, 5
        // divisor로 나눌 수 있는 지 확인 -> for문
        // Arraylist 에 나누어지는 녀석들을 집어넣는다 // 5로 나누어지는 녀석들을 모을 거예요

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                answer.add(arr[i]);
            }
        }
        int[] array;
        if(answer.size() == 0){
            array = new int[]{ -1 };
        } else {
            array = new int[answer.size()]; // 만들어진 ArrayList를 Array로 바꿔주는 부분
            for (int i = 0; i < array.length; i++) {
                array[i] = answer.get(i).intValue();
            }
        }
        Arrays.sort(array);
        return array;
    }
}