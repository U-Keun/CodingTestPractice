#include <iostream>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, k; cin >> n >> k;

    priority_queue<int> q;
    int num;
    while (n--) {
        cin >> num;
        q.push(num);
    }

    int answer = 0;
    while (k--) {
        answer += q.top();
        q.pop();
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}