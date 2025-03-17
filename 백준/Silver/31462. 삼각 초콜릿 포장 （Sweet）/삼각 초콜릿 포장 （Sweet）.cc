#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int n;
vector<vector<char>> frame;
vector<vector<bool>> record;

bool check(int r, int c) {
    if (r == n - 1) return false;
    if (frame[r][c] == 'R') {
        if (record[r + 1][c] || record[r + 1][c + 1]) return false;
        if (frame[r + 1][c] == 'B' || frame[r + 1][c + 1] == 'B') return false;
        record[r + 1][c] = true;
        record[r + 1][c + 1] = true;
        return true;
    } else {
        if (c + 1 > r) return false;
        if (record[r][c + 1] || record[r + 1][c + 1]) return false;
        if (frame[r][c + 1] == 'R' || frame[r + 1][c + 1] == 'R') return false;
        record[r][c + 1] = true;
        record[r + 1][c + 1] = true;
        return true;
    }   
}

int main() {
    FAST_IO;

    cin >> n;
    frame.resize(n, vector<char>(n, ' '));
    record.resize(n, vector<bool>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            cin >> frame[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            if (record[i][j]) continue;
            if (!check(i, j)) {
                cout << "0\n";
                return EXIT_SUCCESS;
            }
        }
    }

    cout << "1\n";
    return EXIT_SUCCESS;
}