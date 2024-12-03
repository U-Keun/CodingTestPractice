#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MOD 1000000007

vector<vector<int>> prod(vector<vector<int>> &A, vector<vector<int>> &B) {
    int row = A.size(), col = B[0].size(), con = A[0].size();
    vector<vector<int>> answer(row, vector<int>(col));
    REP(i, 0, row - 1) {
        REP(j, 0, col - 1) {
            int val = 0;
            REP(k, 0, con - 1) {
                val += (A[i][k] * B[k][j]) % MOD;
                val %= MOD;
            }
            answer[i][j] = val;
        }
    }

    return answer;
}


int main() {
    FAST_IO

    int d;
    cin >> d;
    vector<vector<int>> matrix = {
            { 0, 1, 1, 0, 0, 0, 0, 0 },
            { 1, 0, 1, 1, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1, 0, 0, 0 },
            { 0, 1, 1, 0, 1, 1, 0, 0 },
            { 0, 0, 1, 1, 0, 1, 1, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 1, 0 }
    };

    vector<vector<int>> result = matrix;
    while (d-- > 1) {
        result = prod(result, matrix);
    }

    cout << result[0][0];

    return EXIT_SUCCESS;
}