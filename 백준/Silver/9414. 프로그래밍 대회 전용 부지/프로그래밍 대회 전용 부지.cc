#include <iostream>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define MAX 5000000
#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int t;
    cin >> t;

    priority_queue<int> pq;
    while (t-- > 0) {
        int num;
        cin >> num;
        while (num != 0) {
            pq.emplace(num);
            cin >> num;
        }

        int pow = 1;
        long long total = 0;
        while (!pq.empty()) {
            int cur = pq.top();
            pq.pop();
            long long val = 1;
            REP(i, 0, pow - 1) val *= cur;

            total += val * 2;
            pow++;
            if (total > MAX) break;
        }

        if (!pq.empty() || total > MAX) cout << "Too expensive\n";
        else cout << total << '\n';

        while (!pq.empty()) pq.pop();
    }

    return 0;
}