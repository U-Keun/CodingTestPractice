#include <iostream>
#include <deque>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;
    deque<int> deq;
    for (int i = 1; i <= n; i++) deq.push_back(i);
    
    int l = deq.size(), s = 1;
    while (l > 1) {
        int d_idx = ((long long) s * s * s - 1) % l;
        rotate(deq.begin(), deq.begin() + d_idx, deq.end());

        deq.pop_front();
        l = deq.size();
        s++;
    }

    cout << deq.front() << '\n';

    return EXIT_SUCCESS;
}