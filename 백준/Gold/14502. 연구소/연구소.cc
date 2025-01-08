#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int dx[4] = { -1, 0, 0, 1 },
    dy[4] = {0, 1, -1, 0 };

int n, m;
int lab[8][8];
vector<pair<int, int>> empty_spaces, viruses;

int spread_virus() {
    static int tmp[8][8];
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            tmp[i][j] = lab[i][j];
        }
    }

    queue<pair<int,int>> q;
    for(const auto &v : viruses) {
        q.push(v);
    }

    while(!q.empty()) {
        auto [r, c] = q.front();
        q.pop();

        REP(i, 0, 3) {
            int nr = r + dy[i], nc = c + dx[i];
            if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                if(tmp[nr][nc] == 0) {
                    tmp[nr][nc] = 2;
                    q.push({nr, nc});
                }
            }
        }
    }

    int safe_count = 0;
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            if (tmp[i][j] == 0) safe_count++;
        }
    }

    return safe_count;
}

int main() {
    FAST_IO

    cin >> n >> m;
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            cin >> lab[i][j];
            if (lab[i][j] == 0) {
                empty_spaces.push_back({ i, j });
            } else if (lab[i][j] == 2) {
                viruses.push_back({ i, j });
            }
        }
    }

    int answer = 0, l = empty_spaces.size();
    REP(i, 0, l - 1) {
        REP(j, i + 1, l - 1) {
            REP(k, j + 1, l - 1) {
                auto [r1, c1] = empty_spaces[i];
                auto [r2, c2] = empty_spaces[j];
                auto [r3, c3] = empty_spaces[k];

                lab[r1][c1] = 1;
                lab[r2][c2] = 1;
                lab[r3][c3] = 1;

                answer = max(answer, spread_virus());

                lab[r1][c1] = 0;
                lab[r2][c2] = 0;
                lab[r3][c3] = 0;
            }
        }
    }

    cout << answer << '\n';
    return EXIT_SUCCESS;
}