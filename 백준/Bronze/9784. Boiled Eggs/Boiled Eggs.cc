#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int t, case_num = 1; cin >> t;
    while (t--) {
        int n, p, q; cin >> n >> p >> q;
        vector<int> eggs(n);
        REP(i, 0, n - 1) cin >> eggs[i];
        sort(eggs.begin(), eggs.end());
        int egg_count = 0, weight = 0, idx = 0;
        while (idx < n && egg_count <= p && weight < q) {
            if (egg_count < p && weight + eggs[idx] <= q) {
                egg_count++;
                weight += eggs[idx];
            }
            idx++;
        }
        cout << "Case " << case_num++ << ": " << egg_count << '\n';
    }

    return EXIT_SUCCESS;
}