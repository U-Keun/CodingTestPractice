import java.util.Comparator;
import java.util.LinkedList;
import java.util.Arrays;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(participant));
        parts.sort(Comparator.naturalOrder());
        Arrays.sort(completion);
        for (int i=0; i<completion.length; i++) { //
            if (parts.get(0).equals(completion[i])) parts.remove(0);
            else if (parts.get(1).equals(completion[i])) parts.remove(1);
        }
        answer = parts.get(0);
        return answer;
    }
}