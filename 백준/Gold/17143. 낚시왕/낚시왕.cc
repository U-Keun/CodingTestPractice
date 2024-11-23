#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int row, col, m;

class shark {
    int r, c, s, d, z, i;

public:
    shark(int r, int c, int s, int d, int z, int i)
            : r(r), c(c), d(d), z(z), i(i) {
        if (d < 3) { // 세로
            this->s = s % (2 * row - 2);
        } else { // 가로
            this->s = s % (2 * col - 2);
        }
    }
    void move() { // 상어 이동
        if (d < 3) { // 세로
            if (d == 1) r -= s;
            else r += s;

            while (r < 1 || r > row) {
                if (r < 1) {
                    r = 2 - r;
                    d = 2;
                } else {
                    r = 2 * row - r;
                    d = 1;
                }
            }
        } else { // 가로
            if (d == 3) c += s;
            else c -= s;

            while (c < 1 || c > col) {
                if (c < 1) {
                    c = 2 - c;
                    d = 3;
                } else {
                    c = 2 * col - c;
                    d = 4;
                }
            }
        }
    }

    int getRow() const { return this->r; }
    int getCol() const { return this->c; }
    int getIdx() const { return this->i; }
    int getSize() const { return this->z; }

    void hasDied() { this->z = 0; }

    int operator < (const shark other) const {
        return this->z < other.getSize();
    }
};

int main() {
    FAST_IO

    cin >> row >> col >> m;
    vector<shark> sharks;
    vector<vector<int>> map(row, vector<int>(col, -1));
    int r, c, s, d, z;
    REP(i, 0, m - 1) {
        cin >> r >> c >> s >> d >> z;
        sharks.push_back(shark(r, c, s, d, z, i));
        map[r - 1][c - 1] = i;
    }

    int answer = 0;
    REP(j, 0, col - 1) {
        REP(i, 0, row - 1) {
            int idx = map[i][j];
            if (idx >= 0 && sharks[idx].getSize() > 0) {
                answer += sharks[idx].getSize();
                sharks[idx].hasDied();
                break;
            }
        }

        REP(k, 0, m - 1) {
            int tmp_row = sharks[k].getRow() - 1, tmp_col = sharks[k].getCol() - 1;
            map[tmp_row][tmp_col] = -1;
            sharks[k].move();
        }

        REP(k, 0, m - 1) {
            int tmp_row = sharks[k].getRow() - 1, tmp_col = sharks[k].getCol() - 1;

            int val = map[tmp_row][tmp_col];
            if (val == -1) map[tmp_row][tmp_col] = k;
            else {
                if (sharks[val] < sharks[k]){
                    sharks[val].hasDied();
                    map[tmp_row][tmp_col] = k;
                } else {
                    sharks[k].hasDied();
                }
            }
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}