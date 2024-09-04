#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int answer = 0;
vector<vector<int>> graph;

int dfs(int prev, int current) {
    if (prev >= 0 && graph[current].size() == 1) {
        return 1;
    }
    
    int decider = 0;
    for (int nbd : graph[current]) {
        if (nbd == prev) continue;
        decider += dfs(current, nbd);
    }
    
    if (decider > 0) {
        answer++;
        return 0;
    }
    
    return 1;
}

int solution(int n, vector<vector<int>> lighthouse) {
    
    // graph.clear();
    
    REP(i, 0, n - 1) {
        vector<int> nbds;
        graph.push_back(nbds);
    } 
    
    for (vector<int> edge : lighthouse) {
        graph[edge[0] - 1].emplace_back(edge[1] - 1);
        graph[edge[1] - 1].emplace_back(edge[0] - 1);
    }
    
    dfs(-1, 0);
    
    return answer;
}