#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n; cin >> n;
    priority_queue<int, vector<int>, greater<>> pq;
    int num;
    while (n-- > 0) {
        cin >> num;
        pq.push(num);
    }

    int answer = 0;
    while (pq.size() > 1) {
        int min = pq.top(); pq.pop();
        int secondly_min = pq.top(); pq.pop();
        int val = min + secondly_min;
        answer += val;
        pq.push(val);
    }

    cout << answer;

    return EXIT_SUCCESS;
}