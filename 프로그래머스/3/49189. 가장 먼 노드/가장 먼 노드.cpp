#include <string>
#include <unordered_map>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(int n, vector<vector<int>> edge) {
    unordered_map<int, vector<int>> graph;
    for (vector<int> e : edge) {
        graph[e[0] - 1].push_back(e[1] - 1);
        graph[e[1] - 1].push_back(e[0] - 1);
    }
    
    vector<int> record(n, -1);
    queue<int> q;
    q.push(0);
    record[0] = 0;
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        for (int nbd : graph[cur]) {
            if (record[nbd] < 0) {
                record[nbd] = record[cur] + 1;
                q.push(nbd);
            }
        }
    }
    
    int maximum = 0;
    REP(i, 0, n - 1) maximum = max(maximum, record[i]);
    
    int answer = 0;
    REP(i, 0, n - 1) {
        if (record[i] == maximum) answer++;
    }
    
    return answer;
}