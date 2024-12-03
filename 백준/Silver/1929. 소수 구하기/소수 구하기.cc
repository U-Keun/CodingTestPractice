#include <iostream>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m;
    cin >> m >> n;

    REP(i, m, n) {
        bool is_prime = (i == 1) ? false : true;
        int k = static_cast<int>(sqrt(i));
        REP(j, 2, k) {
            if (i % j == 0) {
                is_prime = false;
                break;
            }
        }

        if (is_prime) cout << i << '\n';
    }

    return EXIT_SUCCESS;
}