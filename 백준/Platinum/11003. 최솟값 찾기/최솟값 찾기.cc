#include <iostream>
#include <vector>
#include <deque>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, l; cin >> n >> l;
    vector<int> numbers(n);
    REP(i, 0, n - 1) cin >> numbers[i];

    deque<int> deq;
    REP(i, 0, n - 1) {
        while (!deq.empty() && deq.front() < i - l + 1) {
            deq.pop_front();
        }

        while (!deq.empty() && numbers[deq.back()] > numbers[i]) {
            deq.pop_back();
        }

        deq.push_back(i);

        cout << numbers[deq.front()] << ' ';
    }

    return EXIT_SUCCESS;
}