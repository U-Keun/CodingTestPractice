#include <iostream>
#include <queue>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;

    priority_queue<int, vector<int>, greater<>> pq;

    int num;
    while (n--) {
        cin >> num;
        pq.push(num);
    }

    int cur = pq.top(), freq = 0, answer = 0;
    while (!pq.empty()) {
        if (cur == pq.top()) {
            freq++;
        } else {
            answer = max(answer, freq);
            freq = 1;
            cur = pq.top();
        }
        pq.pop();
    }

    answer = max(answer, freq);

    cout << answer << '\n';

    return EXIT_SUCCESS;
}