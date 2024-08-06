#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct Person {
    int rowIdx, colIdx, length;
    bool canSmash;
    Person(int rowIdx, int colIdx, int length, bool canSmash)
        : rowIdx(rowIdx), colIdx(colIdx), length(length), canSmash(canSmash) {}
};

int main() {
// int algorithm() {
    FAST_IO

    int N, M;
    cin >> N >> M;

    vector< vector<char> > map(N, vector<char>(M));
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            cin >> map[i][j];
        }
    }

    vector< vector< vector<bool> > > visited(N, vector< vector<bool> >(M, vector<bool>(2, false)));
    queue<Person> q;
    visited[0][0][0] = true;
    q.push(Person(0, 0, 1, true));

    int diff[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    while (!q.empty()) {
        Person tmp = q.front();
        q.pop();

        if (tmp.rowIdx == N - 1 && tmp.colIdx == M - 1) {
            cout << tmp.length << '\n';
            return 0;
        }

        for (auto &d : diff) {
            int newRowIdx = tmp.rowIdx + d[0];
            int newColIdx = tmp.colIdx + d[1];

            if (newRowIdx >= 0 && newRowIdx < N && newColIdx >= 0 && newColIdx < M) {
                if (tmp.canSmash) {
                    if (!visited[newRowIdx][newColIdx][0]) {
                        visited[newRowIdx][newColIdx][0] = true;
                        if (map[newRowIdx][newColIdx] == '1')
                            q.push(Person(newRowIdx, newColIdx, tmp.length + 1, false));
                        else
                            q.push(Person(newRowIdx, newColIdx, tmp.length + 1, true));
                    }
                } else {
                    if (!visited[newRowIdx][newColIdx][1] && map[newRowIdx][newColIdx] == '0') {
                        visited[newRowIdx][newColIdx][1] = true;
                        q.push(Person(newRowIdx, newColIdx, tmp.length + 1, false));
                    }
                }
            }
        }
    }

    cout << -1 << '\n';
    return 0;
}