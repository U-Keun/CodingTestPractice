#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int x, y;

vector<vector<int>> matrix_prod(vector<vector<int>> &A, vector<vector<int>> &B) {
    int value;
    vector<vector<int>> answer(2, vector<int>(2));

    REP(i, 0, 1) {
        REP(j, 0, 1) {
            value = 0;
            REP(k, 0, 1) {
                value += (A[i][k] * B[k][j]) % 100;
                value %= 100;
            }
            answer[i][j] = value;
        }
    }
    return answer;
}

vector<vector<int>> pow(int n) {
    vector<vector<int>> res = {{ 1, 0 }, { 0, 1 }},
        answer = { { 0, 1 }, { y, x }};
    while (n > 0) {
        if (n % 2) res = matrix_prod(res, answer);
        answer = matrix_prod(answer, answer);
        n /= 2;
    }
    return res;
}

int main() {
    FAST_IO

    int a0, a1, n;
    cin >> x >> y >> a0 >> a1 >> n;

    vector<vector<int>> powered = pow(n);
    int answer = (powered[0][0] * a0 + powered[0][1] * a1) % 100;
    if (answer < 10) cout << 0 << answer;
    else cout << answer;

    return EXIT_SUCCESS;
}