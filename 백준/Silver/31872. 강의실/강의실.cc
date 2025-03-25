#include <iostream>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, k; cin >> n >> k;
    priority_queue<int> min_heap;
    for (int i = 0; i < n; i++) {
        int pos; cin >> pos;
        min_heap.push(-pos);
    }

    priority_queue<int> max_heap;
    int cur = 0;
    while (!min_heap.empty()) {
        max_heap.push(cur - min_heap.top());
        cur = min_heap.top();
        min_heap.pop();
    }

    while (k--) max_heap.pop();

    long long answer = 0;
    while (!max_heap.empty()) {
        answer += max_heap.top();
        max_heap.pop();
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}