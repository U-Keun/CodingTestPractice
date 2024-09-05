#include <string>
#include <vector>
#include <functional>

using namespace std;

int solution(int n, vector<vector<int>> lighthouse) {
    vector<vector<int>> graph(n, vector<int>());
    
    for (vector<int> edge : lighthouse) {
        graph[edge[0] - 1].emplace_back(edge[1] - 1);
        graph[edge[1] - 1].emplace_back(edge[0] - 1);
    }
    
    int answer = 0;
    
    function<int(int, int)> dfs = [&](int prev, int cur) -> int {
        if (prev >= 0 && graph[cur].size() == 1) {
            return 1;
        }
        
        int decider = 0;
        for (int nbd : graph[cur]) {
        	if (nbd == prev) continue;
        	decider += dfs(cur, nbd);
    	}
    
    	if (decider > 0) {
        	answer++;
        	return 0;
    	}
    	return 1;
    };
    
    
    dfs(-1, 0);
    
    return answer;
}