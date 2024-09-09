#include <string>
#include <vector>
#include <queue>
#include <map>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<bool> visited(n, false);
    
    map<int, vector<int>> graph;
    for (vector<int> road : roads) {
        graph[road[0]].push_back(road[1]);
        graph[road[1]].push_back(road[0]);
    }
    
    queue<int> nodes;
    nodes.push(destination);
    visited[destination - 1] = true;
    
    int distance = 0;
    map<int, int> record;
    while (!nodes.empty()) {
        int l = nodes.size();
        REP(i, 0, l - 1) {
            int tmp = nodes.front();
            nodes.pop();
            record[tmp] = distance;
            for (int node : graph[tmp]) {
                if (visited[node - 1]) continue;
                visited[node - 1] = true;
                nodes.push(node);
            }
        }
        distance++;
    }
    
    vector<int> answer;
    for (int source : sources) {
        if (!visited[source - 1]) answer.push_back(-1);
        else answer.push_back(record[source]);
    }
    
    return answer;
}