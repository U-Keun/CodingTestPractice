#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> solution(n);
    for (int i = 0; i < n; i++) cin >> solution[i];

    int p1 = 0, p2 = n - 1, tmp;
    int ansIdx1, ansIdx2;
    int minimum = 2000000001;
    while (p1 < p2) {
        tmp = solution[p1] + solution[p2];
        if (abs(tmp) < minimum) {
            ansIdx1 = p1;
            ansIdx2 = p2;
            minimum = abs(tmp);
        }
        if (tmp > 0) p2--;
        else if (tmp < 0) p1++;
        else break;
    }

    cout << solution[ansIdx1] << ' ' << solution[ansIdx2] << '\n';

    return 0;
}