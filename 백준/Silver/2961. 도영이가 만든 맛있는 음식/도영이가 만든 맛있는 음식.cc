#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

vector< pair<int, int> > ingredients;
int n, answer = 1000000000;

void backtracking(const int depth,
                const int prod,
                const int sum,
                const bool isEmpty) {
    if (depth == n) {
        if (!isEmpty) answer = min(answer, abs(prod - sum));
        return;
    }

    backtracking(depth + 1, prod * ingredients[depth].first, sum + ingredients[depth].second, false);
    backtracking(depth + 1, prod, sum, isEmpty);
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n;

    ingredients.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> ingredients[i].first >> ingredients[i].second;
    }

    backtracking(0, 1, 0, true);

    cout << answer << '\n';

    return 0;
}