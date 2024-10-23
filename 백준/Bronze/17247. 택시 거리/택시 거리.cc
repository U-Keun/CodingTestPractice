#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int row, col;
    cin >> row >> col;

    int info;
    vector<pair<int, int>> coord;
    REP(i, 0, row - 1) {
        REP(j, 0, col - 1) {
            cin >> info;
            if (info) {
                coord.push_back({ i, j });
            }
        }
    }

    cout << abs(coord[0].first - coord[1].first) + abs(coord[0].second - coord[1].second);


    return 0;
}