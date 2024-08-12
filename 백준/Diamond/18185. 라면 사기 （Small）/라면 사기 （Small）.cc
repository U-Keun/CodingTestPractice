#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int N;
    cin >> N;

    vector<int> ramens(N);

    for (int i = 0; i < N; i++) cin >> ramens[i];

    long long answer = 0;

    for (int i = 0; i < N; i++) {
        if (ramens[i] == 0)
            continue;

        if (i == N - 1) { // 마지막 공장에 남아있는 라면 처리
            answer += ramens[i] * 3LL;
            ramens[i] = 0;
            continue;
        }

        if (i == N - 2) { // 마지막에서 두 번째 공장에 남아있는 라면 처리
            int val = min(ramens[i], ramens[i + 1]);
            answer += val * 5LL;
            ramens[i] -= val;
            ramens[i + 1] -= val;
            answer += ramens[i] * 3LL;
            ramens[i] = 0;
            continue;
        }

        if (ramens[i + 1] > ramens[i + 2]) {
            // i + 1번째, i + 2번째 공장의 남은 라면 개수를 최대한 맞춰준다.
            int val = min(ramens[i + 1] - ramens[i + 2], ramens[i]);
            answer += val * 5LL;
            ramens[i + 1] -= val;
            ramens[i] -= val;
        }

        // i번째 공장부터 연속 3개의 공장에서 동시에 라면을 최대한 산다.
        int val = min({ramens[i], ramens[i + 1], ramens[i + 2]});
        answer += val * 7LL;
        ramens[i] -= val;
        ramens[i + 1] -= val;
        ramens[i + 2] -= val;

        // i번째 공장에 남아 있는 라면을 산다.
        answer += ramens[i] * 3LL;
        ramens[i] = 0;
    }

    cout << answer << endl;

    return 0;
}