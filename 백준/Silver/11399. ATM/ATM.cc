#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> times(n);
    REP(i, 0, n - 1) cin >> times[i];

    sort(times.begin(), times.end());

    int answer = 0;
    REP(i, 0, n - 1) {
        answer += (n - i) * times[i];
    }

    cout << answer;

    return EXIT_SUCCESS;
}