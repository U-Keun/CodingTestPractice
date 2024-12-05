#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, c, w;
    cin >> n >> c >> w;

    vector<int> trees(n);
    int maximum = 0;
    REP(i, 0, n - 1) {
        cin >> trees[i];
        maximum = max(maximum, trees[i]);
    }

    long long answer = 0;
    REP(i, 1, maximum) {
        long long val = 0;
        for (int tree : trees) {
            int cut = tree / i;
            if (cut == 0) continue;
            int tmp = cut * w * i;
            if (tree % i == 0) {
                tmp -= (cut - 1) * c;
            } else {
                tmp -= cut * c;
            }

            val += max(0, tmp);
        }

        answer = max(answer, val);
    }

    cout << answer;
    return EXIT_SUCCESS;
}