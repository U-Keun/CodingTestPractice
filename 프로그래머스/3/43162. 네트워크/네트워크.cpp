#include <string>
#include <vector>
#include <queue>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(int n, vector<vector<int>> computers) {
    vector<bool> visited(n, false);
    
    queue<int> q;
    int answer = 0;
    REP(i, 0, n - 1) {
        if (visited[i]) continue;
        q.push(i);
        visited[i] = true;
        answer++;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            REP(j, 0, n - 1) {
                if (node == j) continue;
                if (computers[node][j] && !visited[j]) {
                    visited[j] = true;
                    q.push(j);
                }
            }
        }
    }
   
    return answer;
}