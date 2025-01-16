#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int N, K;
    cin >> N >> K;

    vector<int> memo(K + 1, 0);

    REP(i, 0, N - 1) {
        int w, v;
        cin >> w >> v;
        for(int j = K; j >= w; j--){
            memo[j] = max(memo[j], memo[j - w] + v);
        }
    }

    cout << memo[K];

    return EXIT_SUCCESS;
}