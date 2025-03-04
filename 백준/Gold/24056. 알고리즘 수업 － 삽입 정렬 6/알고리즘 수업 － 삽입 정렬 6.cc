#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;

    vector<int> arr(n), target(n);
    REP(i, 0, n - 1) cin >> arr[i];
    REP(i, 0, n - 1) cin >> target[i];

    int idx = 0, dup = -1;
    while (idx < n - 1 && target[idx] <= target[idx + 1]) {
        if (target[idx] == target[idx + 1]) dup = idx;
        idx++;
    }

    REP(i, idx + 1, n - 1) {
        if (arr[i] != target[i]) {
            cout << 0;
            return EXIT_SUCCESS;
        }
    }

    while (idx > 0 && arr[idx] == target[idx]) idx--;

    if (dup != -1) {
        int num = arr[idx];
        while (1 <= dup && num < target[dup]) {
            target[dup] = target[dup - 1];
            dup--;
        }

        if (num < target[dup]) target[dup] = num;
        else target[dup + 1] = num;
    }

    sort(arr.begin(), arr.begin() + idx + 1);
    REP(i, 0, idx) {
        if (arr[i] != target[i]) {
            cout << 0;
            return EXIT_SUCCESS;
        }
    }

    cout << 1;
    return EXIT_SUCCESS;
}