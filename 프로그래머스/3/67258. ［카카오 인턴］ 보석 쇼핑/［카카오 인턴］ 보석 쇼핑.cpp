#include <string>
#include <vector>
#include <set>
#include <unordered_map>

using namespace std;

int count_distinct(vector<string>& gems) {
    set<string> gem_set;
    for (string gem : gems) {
        gem_set.insert(gem);
    }
    return gem_set.size();
}

vector<int> solution(vector<string> gems) {
    int n = count_distinct(gems), l = gems.size();
    vector<int> answer = { 1, l };
    unordered_map<string, int> record;
    
    record[gems[0]] = 1;
    int cur = 1, left = 0, right = 0;
    while (left < l && right < l) {
        if (cur == n) { // 모든 보석 종류가 다 있는 경우
            if (answer[1] - answer[0] > right - left) {
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
            
            record[gems[left]]--;
            if (!record[gems[left]]) cur--;
            left++;
        } else {
            right++;
            if (right == l) break;
            record[gems[right]]++;
            if (record[gems[right]] == 1) cur++;
        }
    }
    
    return answer;
}