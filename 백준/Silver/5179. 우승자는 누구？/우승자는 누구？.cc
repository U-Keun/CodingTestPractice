#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

struct Result {
    int idx, count, score;
};

int main() {
    FAST_IO

    auto compare = [](const Result& a, const Result& b) {
        if (a.count == b.count) {
            return a.score > b.score;
        }
        return a.count < b.count;
    };

    int K; cin >> K;
    REP(i, 1, K) {
        int M, N, P; cin >> M >> N >> P;
        vector<vector<int>> solved(P, vector<int>(27)); // -1이면 푼 것으로 하자
        vector<pair<int, int>> score(P);

        char m;
        int p, t, j;
        REP(k, 0, N - 1) {
            cin >> p >> m >> t >> j;
            if (solved[p - 1][m - 'A'] < 0) continue;

            if (!j) {
                solved[p - 1][m - 'A']++;
                continue;
            }

            score[p - 1].first++;
            score[p - 1].second += t + 20 * solved[p - 1][m - 'A'];
            solved[p - 1][m - 'A'] = -1;
        }

        priority_queue<Result, vector<Result>, decltype(compare)> pq(compare);
        REP(i, 1, P) pq.push({ i, score[i - 1].first, score[i - 1].second });

        cout << "Data Set " << i << ":\n";

        while (!pq.empty()) {
            cout << pq.top().idx << ' '
                << pq.top().count << ' '
                << pq.top().score << '\n';
            pq.pop();
        }

        cout << '\n';
    }

    return EXIT_SUCCESS;
}