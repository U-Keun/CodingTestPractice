#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int N; cin >> N;

    vector<int> constructionTime(N);
    vector<int> inDegree(N, 0);
    vector<vector<int>> graph(N);

    REP(i, 0, N - 1) {
        cin >> constructionTime[i];
        while(true){
            int pre;
            cin >> pre;
            if(pre == -1) break;
            graph[pre - 1].push_back(i);
            inDegree[i]++;
        }
    }

    vector<int> dp(N, 0);

    queue<int> order;
    REP(i, 0, N - 1) {
        if(inDegree[i] == 0) order.push(i);
    }

    while(!order.empty()){
        int tmp = order.front();
        order.pop();

        for(int node : graph[tmp]){
            dp[node] = max(dp[node], dp[tmp] + constructionTime[tmp]);
            inDegree[node]--;
            if(inDegree[node] == 0){
                order.push(node);
            }
        }
    }

    for(int i = 0; i < N; i++){
        cout << dp[i] + constructionTime[i] << '\n';
    }
    return EXIT_SUCCESS;
}