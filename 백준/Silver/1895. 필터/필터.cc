#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<vector<int>> image;

int get_median(int i, int j) {
    vector<int> numbers;
    REP(r, i - 1, i + 1) REP(c, j - 1, j + 1) numbers.emplace_back(image[r][c]);
    sort(numbers.begin(), numbers.end());
    return numbers[4];
}

int main() {
    FAST_IO

    int r, c; cin >> r >> c;
    image.resize(r, vector<int>(c));
    REP(i, 0, r - 1) REP(j, 0, c - 1) cin >> image[i][j];
    int t; cin >> t;

    int answer = 0;
    REP(i, 1, r - 2) {
        REP(j, 1, c - 2) {
            int median = get_median(i, j);
            if (median >= t) answer++;
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}