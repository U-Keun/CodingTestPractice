#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int calculateTime(int target,
                  const vector<vector<int>>& graph,
                  const vector<int>& times,
                  vector<int>& dp) {
    if(dp[target] != -1) return dp[target];

    int maxPrerequisiteTime = 0;
    for(int v : graph[target]) {
        if(dp[v] != -1) maxPrerequisiteTime = max(maxPrerequisiteTime, dp[v]);
        else maxPrerequisiteTime = max(maxPrerequisiteTime, calculateTime(v, graph, times, dp));
    }

    dp[target] = maxPrerequisiteTime + times[target];
    return dp[target];
}

int main() {
    FAST_IO

    int T; cin >> T;

    while(T--){
        int N, K; cin >> N >> K;

        vector<int> times(N);
        REP(i, 0, N - 1) cin >> times[i];

        vector<vector<int>> graph(N);
        REP(i, 0, K - 1) {
            int u, v; cin >> u >> v;
            graph[v - 1].push_back(u - 1);
        }

        int target; cin >> target;
        target -= 1;

        vector<int> dp(N, -1);

        int ans = calculateTime(target, graph, times, dp);
        cout << ans << '\n';
    }
    return EXIT_SUCCESS;
}