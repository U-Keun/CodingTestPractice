#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, k;
    cin >> n >> k;
    vector<long long> memo(k + 1, 0);

    REP(i, 0, n - 1) {
        int value; cin >> value;
        if(value > k) continue;

        memo[value]++;
        REP(j, value + 1, k) memo[j] += memo[j - value];
    }

    cout << memo[k] << '\n';

    return EXIT_SUCCESS;
}