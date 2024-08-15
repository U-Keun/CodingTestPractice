#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int v, e;
    cin >> v >> e;

    vector<vector<int>> graph(v, vector<int>(v, 4000001));
    int a, b, c;
    for (int i = 0; i < e; i++) {
        cin >> a >> b >> c;
        graph[a - 1][b - 1] = c;
    }

    for (int i = 0; i < v; i++) {
        for (int j = 0; j < v; j++) {
            for (int k = 0; k < v; k++) {
                graph[j][k] = min(graph[j][k], graph[j][i] + graph[i][k]);
            }
        }
    }

    int answer = 4000001;
    for (int i = 0; i < v; i++) {
        answer = min(answer, graph[i][i]);
    }

    if (answer == 4000001) cout << -1 << '\n';
    else cout << answer << '\n';

    return 0;
}