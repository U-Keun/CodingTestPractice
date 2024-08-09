#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int n, sharkRow, sharkCol, sharkSize = 2, eatenFishes = 0;
vector< vector<int> > sea;
int dx[4] = { -1, 0, 0, 1 }, dy[4] = { 0, -1, 1, 0 };

void eatFish() {
    eatenFishes++;
    if (sharkSize == eatenFishes) {
        sharkSize++;
        eatenFishes = 0;
    }
}

bool edible(int row, int col) {
    return sea[row][col] > 0 && sea[row][col] < sharkSize;
}

struct Compare {
    bool operator()(pair<int, int> o1, pair<int, int> o2) const {
        if (edible(o1.first, o1.second) && !edible(o2.first, o2.second))
            return false; // o1이 더 높은 우선순위
        if (!edible(o1.first, o1.second) && edible(o2.first, o2.second))
            return true;  // o2가 더 높은 우선순위
        if (o1.first == o2.first)
            return o1.second > o2.second; // 열이 같으면 왼쪽이 우선
        return o1.first > o2.first;       // 행이 같지 않으면 위쪽이 우선
    }
};

int bfs() {
    bool visited[n][n];
    memset(visited, false, sizeof(visited));

    queue< pair<int, int> > q;
    q.push(make_pair(sharkRow, sharkCol));
    visited[sharkRow][sharkCol] = true;
    int time = 0;
    while (!q.empty()) {
        priority_queue<pair<int, int>, vector< pair<int, int> >, Compare> candidates;
        int l = q.size();
        time++;
        for (int i = 0; i < l; i++) {
            pair<int, int> position = q.front();
            q.pop();

            for (int j = 0; j < 4; j++) {
                int newRow = position.first + dx[j],
                    newCol = position.second + dy[j];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n
                    && !visited[newRow][newCol] && sea[newRow][newCol] <= sharkSize) {
                    candidates.push(make_pair(newRow, newCol));
                    visited[newRow][newCol] = true;
                }
            }
        }
        if (!candidates.empty() && edible(candidates.top().first, candidates.top().second)) {
            int r = candidates.top().first, c = candidates.top().second;
            sea[r][c] = 0;
            eatFish();
            sharkRow = r;
            sharkCol = c;
            return time;
        }
        while (!candidates.empty()) {
            q.push(candidates.top());
            candidates.pop();
        }
    }

    return -1;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n;

    sea.resize(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> sea[i][j];
            if (sea[i][j] == 9) {
                sharkRow = i;
                sharkCol = j;
                sea[i][j] = 0;
            }
        }
    }

    int totalTime = 0, time;
    while ((time = bfs()) > 0) {
        totalTime += time;
    }

    cout << totalTime << '\n';

    return 0;
}