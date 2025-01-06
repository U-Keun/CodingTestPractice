#include <iostream>
#include <deque>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, k, m; cin >> n >> k >> m;
    deque<int> deq;
    REP(i, 1, n) deq.push_back(i);

    bool clockwise = true;
    int removed = 0;
    while (!deq.empty()) {
        if (clockwise) {
            REP(i, 1, k - 1) {
                int cur = deq.front(); deq.pop_front();
                deq.push_back(cur);
            }

            cout << deq.front() << '\n';
            deq.pop_front();
            removed++;
        } else {
            REP(i, 1, k - 1) {
                int cur = deq.back(); deq.pop_back();
                deq.push_front(cur);
            }

            cout << deq.back() << '\n';
            deq.pop_back();
            removed++;
        }

        if (removed == m) {
            clockwise = !clockwise;
            removed = 0;
        }
    }

    return EXIT_SUCCESS;
}