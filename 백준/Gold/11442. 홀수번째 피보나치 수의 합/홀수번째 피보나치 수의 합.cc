#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

ll MOD = 1000000007;

// n * n 행렬의 곱 정의
vector< vector<ll> > operator * (
    vector< vector<ll> >& A,
    vector< vector<ll> >& B) {
    const int n = A.size();
    vector< vector<ll> > ans(n, vector<ll>(n));
    ll value;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            value = 0;
            for (int k = 0; k < n; k++) {
                value += A[i][k] * B[k][j];
            }
            ans[i][j] = value % MOD;
        }
    }
    return ans;
}

int main() {
// int algorithm() {
    FAST_IO;

    ll n;
    cin >> n;

    if (n % 2) n++;

    vector< vector<ll> > res(2, vector<ll>(2));
    res[0][0] = 1;
    res[1][1] = 1;

    vector< vector<ll> > fibonacciPow(2, vector<ll>(2));
    fibonacciPow[0][1] = 1;
    fibonacciPow[1][0] = 1;
    fibonacciPow[1][1] = 1;

    while (n > 0) {
        if (n % 2) res = res * fibonacciPow;
        fibonacciPow = fibonacciPow * fibonacciPow;
        n /= 2;
    }

    cout << res[1][0] << '\n';

    return 0;
}