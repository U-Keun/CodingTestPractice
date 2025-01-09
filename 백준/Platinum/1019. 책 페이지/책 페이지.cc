#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    long long number; cin >> number;

    vector<long long> answer(10);

    long long mod = 1;
    while (number / mod > 0) {
        long long count = number / (mod * 10);

        if (count > 0) {
            answer[0] += (count - 1) * mod;
            REP(i, 1, 9) {
                answer[i] += count * mod;
            }
        }

        long long r = number % (mod * 10), bound = r / mod;
        if (count > 0) {
            if (bound == 0) answer[0] += (r % mod) + 1;
            else answer[0] += mod;
        }

        REP(i, 1, bound - 1) answer[i] += mod;

        if (bound > 0) answer[bound] += (r % mod) + 1;

        mod *= 10;
    }

    REP(i, 0, 9) {
        cout << answer[i];
        if (i < 9) cout << ' ';
    }
    cout << '\n';

    return EXIT_SUCCESS;
}