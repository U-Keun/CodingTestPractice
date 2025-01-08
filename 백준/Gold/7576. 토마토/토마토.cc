#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int dx[4] = { -1, 0, 0, 1 },
    dy[4] = {0, 1, -1, 0 };

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    vector<vector<int>> basket(m, vector<int>(n));
    int unripe = 0;
    queue<pair<int,int>> orders;

    REP(i, 0, m - 1) {
        REP(j, 0, n - 1) {
            int tomato;
            cin >> tomato;
            basket[i][j] = tomato;
            if(tomato == 1) {
                orders.push({i, j});
            } else if(tomato == 0) {
                unripe++;
            }
        }
    }

    int answer = -1;
    while(!orders.empty()) {
        int tries = orders.size();

        REP(i, 0, tries - 1) {
            auto [row, col] = orders.front();
            orders.pop();

            REP(j, 0, 3) {
                int ny = row + dy[j];
                int nx = col + dx[j];

                if(ny >= 0 && ny < m && nx >= 0 && nx < n
                   && basket[ny][nx] == 0) {
                    basket[ny][nx] = 1;
                    unripe--;
                    orders.push({ny, nx});
                }
            }
        }
        answer++;
    }

    if(unripe > 0) cout << -1 << "\n";
    else cout << answer << "\n";

    return EXIT_SUCCESS;
}