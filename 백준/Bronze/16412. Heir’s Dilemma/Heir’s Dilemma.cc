#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

bool is_legit(int num) {
    vector<bool> used(10);
    int copy = num;
    while (copy > 0) {
        int val = copy % 10;
        if (val == 0 || used[val]) return false;
        used[val] = true;
        copy /= 10;
    }

    REP(i, 1, 9) {
        if (used[i]) {
            if (num % i == 0) continue;
            return false;
        }
    }

    return true;
}

int main() {
    FAST_IO

    int l, h;
    cin >> l >> h;

    int answer = 0;
    REP(i, l, h) {
        if (is_legit(i)) answer++;
    }

    cout << answer;

    return 0;
}