#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(vector<vector<int>> scores) {
    vector<vector<int>> candidates;
    int l = scores.size();
    REP(i, 1, l - 1) {
        if (scores[i][0] + scores[i][1] > scores[0][0] + scores[0][1]) {
            candidates.emplace_back(scores[i]);
        }
    }
    
    for (vector<int> candidate : candidates) {
        if (candidate[0] > scores[0][0] && candidate[1] > scores[0][1]) return -1;
    }
    
    sort(candidates.begin(), candidates.end());
    int m = candidates.size(), deleteCount = 0;
    REP(i, 0, m - 1) {
        REP(j, i + 1, m - 1) {
	    if (candidates[i][0] == candidates[j][0]) continue;
            if (candidates[i][1] < candidates[j][1]) {
		deleteCount++;
		break;            
            }
        }
    }
    
    return candidates.size() - deleteCount + 1;
}
