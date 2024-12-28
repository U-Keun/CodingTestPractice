#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    vector<int> numbers(n);
    REP(i, 0, n - 1) cin >> numbers[i];
    pair<int, int> base;
    int chance, answer = 0;
    REP(i, 0, n - 1) {
        base = { -1, -1 };
        chance = -1;
        int idx = i;
        while (idx < n) {
            if (base.first == -1) {
                base.first = numbers[idx++];
                base.second = 1;
                continue;
            }

            if (base.first == numbers[idx]) {
                base.second++;
                idx++;
                continue;
            }

            if (chance == -1) {
                chance = numbers[idx++];
                continue;
            }

            if (numbers[idx] == chance) {
                idx++;
                continue;
            }

            break;
        }

        answer = max(answer, base.second);
    }

    cout << answer;

    return EXIT_SUCCESS;
}