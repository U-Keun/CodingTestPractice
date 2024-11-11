#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> _info;
vector<vector<int>> _edges;
vector<bool> visited;

void dfs(int sheep, int wolf, int& ans) {
    if (sheep > wolf) {
        ans = max(ans, sheep);
    } else return;
    
    for (auto& edge : _edges) {
        if (visited[edge[0]] && !visited[edge[1]]) {
            visited[edge[1]] = true;
            if (_info[edge[1]]) dfs(sheep, wolf + 1, ans);
            else dfs(sheep + 1, wolf, ans);
            visited[edge[1]] = false;
        }
    }
    
}

int solution(vector<int> info, vector<vector<int>> edges) {
    _info = info;
    _edges = edges;
    
    int n = info.size();
    visited.resize(n, false);
    
    visited[0] = true;
    int answer = 1;
    dfs(1, 0, answer);
    
    return answer;
}