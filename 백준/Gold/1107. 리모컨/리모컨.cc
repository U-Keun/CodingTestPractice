#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

bool number_buttons[10];

bool can_make(int n) {
    if (n == 0) return number_buttons[0];

    while (n > 0) {
        int digit = n % 10;
        if (!number_buttons[digit]) return false;
        n /= 10;
    }

    return true;
}

int get_length(int n) {
    if (n == 0) return 1;
    int len = 0;
    while (n > 0) {
        n /= 10;
        len++;
    }
    return len;
}

int main() {
    FAST_IO

    int n, m; cin >> n >> m;

    REP(i, 0, 9) number_buttons[i] = true;
    REP(i, 0, m - 1) {
        int broken; cin >> broken;
        number_buttons[broken] = false;
    }

    if (m == 10) {
        cout << abs(n - 100) << '\n';
        return EXIT_SUCCESS;
    }

    int upper = n, lower = n, answer = 0;
    while (!can_make(upper) && !can_make(lower)) {
        upper++;
        if (lower > 0) lower--;
        answer++;
    }

    int buttons_needed = INT_MAX;
    if (can_make(upper)) buttons_needed = min(buttons_needed, get_length(upper));
    if (can_make(lower)) buttons_needed = min(buttons_needed, get_length(lower));

    int result = min(answer + buttons_needed, abs(n - 100));

    cout << result;

    return EXIT_SUCCESS;
}