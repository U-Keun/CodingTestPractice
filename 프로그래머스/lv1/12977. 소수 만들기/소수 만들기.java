import java.util.*;

class Solution  {
    public int solution(int[] nums) {
        int val = 0;
        int answer = 0;
        boolean isSosu = true;
        ArrayList<Integer> set = new ArrayList<>();

        for(int i =0; i < nums.length - 2; i++) {

            for(int j=i+1; j <nums.length - 1; j++) {

                for(int z = j+1; z <nums.length; z++) {
                    val = nums[i] + nums[j] + nums[z];
                    set.add(val);
                }
            }
        }
        Integer[] arr = set.toArray(new Integer[0]);
        for(int arr_val: arr) {
            isSosu = true;
            for(int x = 2; x <= Math.sqrt(arr_val); x++) {
                if(arr_val% x== 0) {
                    isSosu = false;
                    break;
                }
            }
            if(isSosu)
                answer ++;
        }

        System.out.println(answer);
        return answer;
    }
}