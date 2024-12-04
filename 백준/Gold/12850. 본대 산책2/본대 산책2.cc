#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define ll long long
#define MOD 1000000007

vector<vector<ll>> prod(vector<vector<ll>> &A, vector<vector<ll>> &B) {
    int row = A.size(), col = B[0].size(), con = A[0].size();
    vector<vector<ll>> answer(row, vector<ll>(col));
    REP(i, 0, row - 1) {
        REP(j, 0, col - 1) {
            long long val = 0;
            REP(k, 0, con - 1) {
                val += A[i][k] * B[k][j];
            }
            answer[i][j] = val % MOD;
        }
    }

    return answer;
}

vector<vector<ll>> matrix_power(vector<vector<ll>> &A, int p) {
    int n = A.size();
    vector<vector<ll>> res(n, vector<ll>(n));
    for (int i = 0; i < n; i++) {
        res[i][i] = 1;
    }
    while (p > 0) {
        if (p % 2 == 1) {
            res = prod(res, A);
        }
        A = prod(A, A);
        p /= 2;
    }
    return res;
}

int main() {
    FAST_IO

    int d;
    cin >> d;
    vector<vector<ll>> matrix = {
            { 0, 1, 1, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 1, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1, 0, 0, 0 },
            { 0, 1, 1, 0, 1, 1, 0, 0 },
            { 0, 0, 1, 1, 0, 1, 1, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 1, 0 }
    };

    vector<vector<ll>> result = matrix_power(matrix, d);

    cout << result[0][0];

    return EXIT_SUCCESS;
}