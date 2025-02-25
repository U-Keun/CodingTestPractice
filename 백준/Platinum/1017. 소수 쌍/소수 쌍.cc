#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<vector<int>> graph;
vector<int> record, numbers;
vector<bool> visited;

vector<bool> is_prime;
int pair_count;

bool find_matching(int idx) {
    for (const auto &nbd : graph[idx]) {
        if (visited[nbd]) continue;
        visited[nbd] = true;
        if (record[nbd] == -1 || find_matching(record[nbd])) {
            record[nbd] = idx;
            return true;
        }
    }
    return false;
}

int main() {
    FAST_IO

    int n; cin >> n;
    numbers.resize(n);
    REP(i, 0, n - 1) cin >> numbers[i];

    is_prime.resize(2000, true);
    is_prime[1] = false;
    REP(i, 2, 1999) {
        if (is_prime[i]) {
            for (int j = i + i; j <= 1999; j += i) is_prime[j] = false;
        }
    }

    graph.resize(n);
    REP(i, 0, n - 2) {
        REP(j, i + 1, n - 1) {
            int val = numbers[i] + numbers[j];
            if (is_prime[val]) {
                graph[i].push_back(j);
                graph[j].push_back(i);
            }
        }
    }

    vector<int> answer;
    for (const auto& nbd: graph[0]) {
        int val = numbers[0] + numbers[nbd];
        if (!is_prime[val]) continue;
        record.assign(n, -1);
        record[nbd] = 0;
        record[0] = nbd;
        pair_count = 0;
        REP(i, 1, n - 1) {
            if (i == nbd) continue;
            visited.assign(n, false);
            visited[0] = visited[nbd] = true;
            if (find_matching(i)) pair_count++;
        }

        if (pair_count == n - 2) answer.push_back(numbers[nbd]);
    }

    if (answer.empty()) cout << -1;
    else {
        sort(answer.begin(), answer.end());
        for (const auto &num : answer) cout << num << ' ';
    }

    return EXIT_SUCCESS;
}