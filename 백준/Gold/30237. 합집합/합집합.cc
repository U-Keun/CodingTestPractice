#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

int main() {
// int algorithm() {
    FAST_IO

    int t;
    cin >> t;

    while(t-- > 0) {
        int n;
        cin >> n;
        vector<ll> sets(n, 0L);

        int k;
        ll unionSet = 0L;
        for (int i = 0; i < n; i++) {
            cin >> k;
            int elt;

            for (int j = 0; j < k; j++) {
                cin >> elt;
                sets[i] |= (1L << (elt - 1));
            }
            unionSet |= sets[i];
        }

        int maxCount = 0;
        for (ll bit = 1; unionSet; bit <<= 1) {
            if (unionSet & bit) {
                ll val = 0L;
                for (ll set : sets) {
                    if (!(set & bit)) {
                        val |= set;
                    }
                }
                maxCount = max(maxCount,  __builtin_popcountll(val));
            }
            unionSet &= ~bit;
        }
        cout << maxCount << '\n';

    }

    return 0;
}