#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    vector<int> mark(n + 1, -1);
    for (int num : gates) mark[num] = 1;
    for (int num : summits) mark[num] = 0;
    
    vector<vector<pair<int, int>>> graph(n + 1);
    for (auto& edge : paths) {
        graph[edge[0]].push_back({ edge[1], edge[2] });
        graph[edge[1]].push_back({ edge[0], edge[2] });
    }
    
    vector<int> answer = { 0, 10000001 }, record;
    queue<pair<int, int>> q;
    for (int gate : gates) {
        q.push({ gate, 0 });
        record.resize(n + 1, 10000001);
        while (!q.empty()) {
            int cur = q.front().first, intensity = q.front().second;
            q.pop();
            for (auto& edge : graph[cur]) {
                if (mark[edge.first] != 1) {   
                    int val = max(intensity, edge.second);
                    if (record[edge.first] > val) {
                        record[edge.first] = val;
                        if (mark[edge.first] == -1) q.push({ edge.first, record[edge.first] });
                    }
                }
            }
        }
        
        for (int summit : summits) {
            if (answer[1] > record[summit]) {
                answer[0] = summit;
                answer[1] = record[summit];
            } else if (answer[1] == record[summit]) answer[0] = min(summit, answer[0]);
        }
    }   
    
    return answer;
}