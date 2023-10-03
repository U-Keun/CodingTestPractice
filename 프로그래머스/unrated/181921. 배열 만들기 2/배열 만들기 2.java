import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        numbers.add(5);
        ArrayList<Integer> array = new ArrayList<>();
        while (!numbers.isEmpty() && numbers.peek() <= r) {
            int tmp = numbers.poll();
            if (tmp >= l && tmp <= r) {
                array.add(tmp);
            }
            if (tmp % 10 == 5) numbers.add(tmp * 10);
            else {
                numbers.add(tmp + 5);
                numbers.add(tmp * 10);
            }
        }
        if (array.size() == 0) return new int[]{-1};
        int[] answer = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            answer[i] = array.get(i);
        }
        return answer;
    }
}