import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> kinds = new HashSet<>();
        for (int i:nums) {
            kinds.add(i);
        }
        answer = Math.min(kinds.size(), nums.length/2);
        return answer;
    }
}