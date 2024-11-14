#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int n, answer = 0;
vector<int> numbers;

void backtracking(int idx, int groups, int sum, int cur) {
    if (groups > 4) return;
    if (idx == n) {
        if (groups == 4) answer = max(answer, sum + cur);
        return;
    }

    backtracking(idx + 1, groups, sum, cur * numbers[idx]);
    backtracking(idx + 1, groups + 1, sum + cur, numbers[idx]);
}

int main() {
    FAST_IO

    cin >> n;
    numbers.resize(n);
    REP(i, 0, n - 1) cin >> numbers[i];

    backtracking(1, 1, 0, numbers[0]);
    cout << answer;

    return 0;
}