#include <iostream>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int t, n, m;
    cin >> t >> n;
    vector<int> A(n + 1);
    REP(i, 1, n) {
        cin >> A[i];
        A[i] += A[i - 1];
    }

    cin >> m;
    vector<int> B(m + 1);
    REP(i, 1, m) {
        cin >> B[i];
        B[i] += B[i - 1];
    }

    unordered_map<int, int> record;
    REP(i, 1, m) {
        REP(j, i, m) {
            record[B[j] - B[i - 1]]++;
        }
    }

    long long answer = 0;
    REP(i, 1, n) {
        REP(j, i, n) {
            answer += record[t - A[j] + A[i - 1]];
        }
    }

    cout << answer;
    return 0;
}