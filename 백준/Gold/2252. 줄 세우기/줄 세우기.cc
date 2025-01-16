#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int N, M; cin >> N >> M;

    vector<vector<int>> graph(N);
    vector<int> indegree(N + 1, 0);

    REP(i, 0, M - 1) {
        int a, b; cin >> a >> b;
        graph[a-1].push_back(b);
        indegree[b]++;
    }

    queue<int> q;
    REP(i, 1, N) {
        if (!indegree[i]) q.push(i);
    }

    while(!q.empty()){
        int tmp = q.front(); q.pop();

        for(int nxt : graph[tmp-1]){
            indegree[nxt]--;
            if(indegree[nxt] == 0){
                q.push(nxt);
            }
        }
        cout << tmp << ' ';
    }

    return EXIT_SUCCESS;
}