#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, k;
    cin >> n >> k;
    vector<bool> mark(k + 1, false);
    vector<int> prime;
    REP(i, 2, k) {
        if (mark[i]) continue;
        prime.push_back(i);
        for (int j = i * i; j <= k; j += i) {
            mark[j] = true;
        }
    }

    int answer = min(n, k);
    REP(i, k + 1, n) {
        int target = i;
        for (int p : prime) {
            while (target % p == 0) target /= p;
        }

        if (target == 1) answer++;
    }

    cout << answer;

    return 0;
}