#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, num; cin >> n;
    vector<int> pos, neg;
    while (n-- > 0) {
        cin >> num;
        if (num > 0) pos.emplace_back(num);
        else neg.emplace_back(num);
    }

    sort(pos.begin(), pos.end(), greater<>());
    sort(neg.begin(), neg.end());

    int answer = 0;

    int l = pos.size();
    for (int i = 0; i < l; i += 2) {
        if (i + 1 == l) {
            answer += pos[i];
            continue;
        }

        if (pos[i + 1] == 1) {
            answer += pos[i] + 1;
            continue;
        }
        answer += pos[i] * pos[i + 1];
    }

    int m = neg.size();
    for (int i = 0; i < m; i += 2) {
        if (i + 1 == m) {
            answer += neg[i];
            continue;
        }

        answer += neg[i] * neg[i + 1];
    }

    cout << answer;

    return EXIT_SUCCESS;
}