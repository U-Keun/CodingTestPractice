#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dx[6] = { -1, 1, 0, 0, 0, 0 },
    dy[6] = { 0, 0, -1, 1, 0, 0 },
    dz[6] = { 0, 0, 0, 0, -1, 1 };

int main() {
    FAST_IO

    int M, N, H;
    cin >> M >> N >> H;

    vector<vector<vector<int>>> basket(H, vector<vector<int>>(N, vector<int>(M)));
    queue<tuple<int, int, int>> q;
    int unripe = 0;
    REP(i, 0, H - 1) {
        REP(j, 0, N - 1) {
            REP(k, 0, M - 1) {
                cin >> basket[i][j][k];
                if (basket[i][j][k] == 0) unripe++;
                else if (basket[i][j][k] == 1) {
                    q.emplace(i, j, k);
                }
            }
        }
    }

    int answer = -1;
    while (!q.empty()) {
        int l = q.size();
        while (l-- > 0) {
            int z = get<0>(q.front()),
                y = get<1>(q.front()),
                x = get<2>(q.front());
            q.pop();
            REP(i, 0, 5) {
                int nz = z + dz[i], ny = y + dy[i], nx = x + dx[i];
                if (nz >= 0 && nz < H
                    && ny >= 0 && ny < N
                    && nx >= 0 && nx < M
                    && basket[nz][ny][nx] == 0) {
                    basket[nz][ny][nx]++;
                    unripe--;
                    q.emplace(nz, ny, nx);
                }
            }
        }
        answer++;
    }

    if (unripe) cout << -1;
    else cout << answer;;

    return 0;
}