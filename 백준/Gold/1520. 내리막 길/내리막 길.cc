#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int N, M;
vector<vector<int>> map_grid;
vector<vector<long long>> memo;

int dx[4] = {0, 0, 1, -1},
    dy[4] = {1, -1, 0, 0};

long long dfs(int i, int j) {
    if (i == N - 1 && j == M - 1) {
        return 1;
    }

    if (memo[i][j] != -1) {
        return memo[i][j];
    }

    long long count = 0;

    REP(k, 0, 3) {
        int ni = i + dy[k], nj = j + dx[k];

        if (ni >= 0 && ni < N && nj >= 0 && nj < M && map_grid[ni][nj] < map_grid[i][j]) {
            count += dfs(ni, nj);
        }
    }

    memo[i][j] = count;
    return count;
}

int main() {
    FAST_IO

    cin >> N >> M;
    map_grid.assign(N, vector<int>(M));
    REP(i, 0, N - 1) REP(j, 0, M - 1) cin >> map_grid[i][j];

    memo.assign(N, vector<long long>(M, -1));

    cout << dfs(0, 0);

    return EXIT_SUCCESS;
}