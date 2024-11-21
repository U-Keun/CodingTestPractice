#include <iostream>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int n, k;
vector<int> numbers;
unordered_map<int, int> record;

void back_tracking(int cnt, int bitmask, int made) {
    if (cnt == k) {
        record[made]++;
        return;
    }

    REP(i, 0, n - 1) {
        if ((bitmask & (1 << i)) != 0) continue;
        int val = bitmask;
        val |= 1 << i;
        if (numbers[i] < 10) back_tracking(cnt + 1, val, made * 10 + numbers[i]);
        else back_tracking(cnt + 1, val, made * 100 + numbers[i]);
    }
}

int main() {
    FAST_IO

    cin >> n >> k;
    numbers.resize(n);
    REP(i, 0, n - 1) cin >> numbers[i];

    back_tracking(0, 0, 0);

    cout << record.size();

    return EXIT_SUCCESS;
}