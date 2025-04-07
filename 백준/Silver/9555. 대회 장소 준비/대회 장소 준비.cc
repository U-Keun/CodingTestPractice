#include <iostream>
#include <vector>
#include <set>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int seats[100][100];
int n, m,
    dr[8] = { -1, -1, -1, 0, 0, 1, 1, 1 },
    dc[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };

bool is_valid_coord(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m;
}

int main() {
    FAST_IO;

    int t; cin >> t;
    while (t--) {
        cin >> n >> m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cin >> seats[i][j];
            }
        }

        set<int> adv;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int id = seats[i][j];
                if (id == -1) continue;

                for (int dir = 0; dir < 8; dir++) {
                    int nr = i + dr[dir], nc = j + dc[dir];
                    if (is_valid_coord(nr, nc) && seats[nr][nc] == id) {
                        adv.insert(id);
                        break;
                    }
                }
            }
            
        }
        cout << adv.size() << '\n';
    }

    return EXIT_SUCCESS;
}