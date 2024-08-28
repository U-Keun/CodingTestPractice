#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int n;
vector<vector<int>> seats;
vector<pair<int, int>> positions;
int dx[4] = {-1, 0, 0, 1},
    dy[4] = {0, -1, 1, 0};

bool isValidPosition(int row, int col) {
    return row >= 0 && row < n
        && col >= 0 && col < n;
}

pair<int, int> findIsolatedSeat() {
    pair<int, int> result(-1, -1);
    int count = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (seats[i][j] != 0) continue;
            int val = 0;
            for (int k = 0; k < 4; k++) {
                int row = i + dy[k], col = j + dx[k];
                if (isValidPosition(row, col) && !seats[row][col]) val++;
            }
            if (count < val) {
                result.first = i;
                result.second = j;
                count = val;
            }

            if (count == 4) return result;
        }
    }
    return result;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n;

    seats.resize(n, vector<int>(n));

    int students = n * n, idx;
    positions.resize(students, make_pair(-1, -1));
    vector<vector<int>> preferences(students, vector<int>(4));
    for (int i = 0; i < students; i++) {
        cin >> idx
            >> preferences[idx - 1][0]
            >> preferences[idx - 1][1]
            >> preferences[idx - 1][2]
            >> preferences[idx - 1][3];

        if (positions[preferences[idx - 1][0] - 1].first < 0 &&
            positions[preferences[idx - 1][1] - 1].first < 0 &&
            positions[preferences[idx - 1][2] - 1].first < 0 &&
            positions[preferences[idx - 1][3] - 1].first < 0) {
            positions[idx - 1] = findIsolatedSeat();
            seats[positions[idx - 1].first][positions[idx - 1].second] = idx;
            continue;
        }

        map<pair<int, int>, int> candidates;
        for (int prefer : preferences[idx - 1]) {
            if (positions[prefer - 1].first >= 0) {
                for (int i = 0; i < 4; i++) {
                    int row = positions[prefer - 1].first + dy[i],
                        col = positions[prefer - 1].second + dx[i];
                    if (isValidPosition(row, col) && !seats[row][col]) {
                        pair<int, int> candidate = make_pair(row, col);
                        auto it = candidates.find(candidate);
                        if (it == candidates.end()) candidates.insert({ candidate, 1 });
                        else (it->second)++;
                    }
                }
            }
        }

        pair<int, int> decision = make_pair(n, n);
        int maxPref = 0, maxEmptyNbd = 0;
        for (const auto& candidate : candidates) {
            auto& candidatePos = candidate.first;
            int candidateValue = candidate.second;

            int emptyNbd = 0;
            for (int  i = 0; i < 4; i++) {
                int row = candidatePos.first + dy[i], col = candidatePos.second + dx[i];
                if (isValidPosition(row, col) && !seats[row][col]) emptyNbd++;
            }

            if (candidateValue > maxPref) {
                decision = candidatePos;
                maxPref = candidateValue;
                maxEmptyNbd = emptyNbd;
            } else if (candidateValue == maxPref) {
                if (emptyNbd > maxEmptyNbd) {
                    decision = candidatePos;
                    maxPref = candidateValue;
                    maxEmptyNbd = emptyNbd;
                } else if (emptyNbd == maxEmptyNbd) {
                    if ((decision.first == candidatePos.first && decision.second > candidatePos.second) ||
                        decision.first > candidatePos.first) {
                        decision = candidatePos;
                    }
                }
            }
        }

        if (decision.first == n && decision.second == n) {
            positions[idx - 1] = findIsolatedSeat();
            seats[positions[idx - 1].first][positions[idx - 1].second] = idx;
            continue;
        }

        positions[idx - 1] = decision;
        seats[decision.first][decision.second] = idx;
    }

    int answer = 0;
    for (int i = 0; i < students; i++) {
        pair<int, int> current = positions[i];
        int count = 0;

        for (int prefer : preferences[i]) {
            pair<int, int> f = positions[prefer - 1];
            if ((current.first == f.first && current.second == f.second + 1) ||
                (current.first == f.first && current.second == f.second - 1) ||
                (current.first == f.first + 1 && current.second == f.second) ||
                (current.first == f.first - 1 && current.second == f.second)) {
                count++;
            }
        }

        switch(count) {
            case 1:
                answer += 1;
                break;
            case 2:
                answer += 10;
                break;
            case 3:
                answer += 100;
                break;
            case 4:
                answer += 1000;
                break;
            default:
                break;
        }
    }

    cout << answer;

    return 0;
}