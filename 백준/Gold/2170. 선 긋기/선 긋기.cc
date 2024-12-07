#include <iostream>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define INF 1000000001

int main() {
    FAST_IO

    int n; cin >> n;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    int s, e;
    while (n-- > 0) {
        cin >> s >> e;
        pq.emplace(s, e);
    }

    int answer = 0, from = -INF, to = -INF;
    while (!pq.empty()) {
        int f = pq.top().first, t = pq.top().second;
        pq.pop();

        if (to < f) {
            answer += to - from;
            from = f;
            to = t;
        } else if (f <= to && t > to) {
            to = t;
        }
    }
    answer += to - from;

    cout << answer;

    return EXIT_SUCCESS;
}