import java.util.*;
class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int j:numbers) list.add(j);
        for (Integer i:result((ArrayList<Integer>) list)) {
            if(i == target) answer++;
        }
        return answer;
    }
    ArrayList<Integer> result(ArrayList<Integer> input) {
        ArrayList<Integer> answer = new ArrayList<>();
        if (input.size() == 1) {
            answer.add(input.get(0));
            answer.add(-input.get(0));
            return answer;
        }
        Integer k = input.get(input.size() - 1);
        input.remove(input.size() - 1);
        for (Integer i:result(input)) {
            answer.add(i + k);
            answer.add(i - k);
        }
        return answer;
    }
}