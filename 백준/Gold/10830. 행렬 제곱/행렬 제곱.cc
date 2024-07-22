#define MOD 1000
#define ll long long

#include <iostream>
#include <vector>

using namespace std;
ll n, m;

vector<vector<ll>> matrix_product(vector<vector<ll>> A, vector<vector<ll>> B) {
    vector<vector<ll>> answer(n, vector<ll>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ll value = 0;
            for (int k = 0; k < n; k++) {
                value += A[i][k] * B[k][j];
            }
            answer[i][j] = value % MOD;
        }
    }
    return answer;
}

vector<vector<ll>> matrix_power(vector<vector<ll>> matrix, long long k) {
    vector<vector<ll>> res(n, vector<ll>(n));
    for (int i = 0; i < n; i++) {
        res[i][i] = 1;
    }
    while (k > 0) {
        if (k % 2 == 1) {
            res = matrix_product(res, matrix);
        }
        matrix = matrix_product(matrix, matrix);
        k /= 2;
    }
    return res;
}

int main()
{
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);

    cin >> n >> m;

    vector<vector<ll>> matrix(n, vector<ll>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> matrix[i][j];
        }
    }

    matrix = matrix_power(matrix, m);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << '\n';
    }

    return 0;
}