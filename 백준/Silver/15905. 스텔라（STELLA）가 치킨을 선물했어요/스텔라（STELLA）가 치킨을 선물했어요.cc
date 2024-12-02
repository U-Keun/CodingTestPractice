#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    priority_queue<pair<int, int>> pq;
    int a, b;
    while (n-- > 0) {
        cin >> a >> b;
        pq.emplace(a, -b);
    }

    if (pq.size() <= 5) {
        cout << 0;
        return 0;
    }

    for (int i = 0; i < 4; i++) pq.pop();

    int p = pq.top().first, answer = 0;
    pq.pop();
    while (!pq.empty() && p == pq.top().first) {
        answer++;
        pq.pop();
    }

    cout << answer;

    return EXIT_SUCCESS;
}