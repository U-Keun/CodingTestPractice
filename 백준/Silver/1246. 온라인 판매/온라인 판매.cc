#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<int> numbers(m);
    REP(i, 0, m -1) cin >> numbers[i];

    sort(numbers.begin(), numbers.end());

    int answer = 0, idx = -1;
    REP(i, 0, m - 1) {
        if (answer < min(n, (m - i)) * numbers[i]) {
            answer = min(n, (m - i)) * numbers[i];
            idx = i;
        }
    }

    cout << numbers[idx] << ' ' << answer;

    return 0;
}