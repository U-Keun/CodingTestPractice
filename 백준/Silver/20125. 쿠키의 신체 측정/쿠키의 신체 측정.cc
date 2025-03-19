#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

vector<vector<char>> frame;

int main() {
    FAST_IO;

    int n; cin >> n;
    frame.resize(n, vector<char>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> frame[i][j];
        }
    }

    // find heart
    pair<int, int> heart = { -1, -1 };
    bool found = false;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (frame[i][j] == '_') continue;
            heart.first = i + 1;
            heart.second = j;
            found = true;
            break;
        }
        if (found) break;
    }

    int l_arm = 0, idx = heart.second - 1;
    while (idx >= 0 && frame[heart.first][idx--] == '*') l_arm++;

    int r_arm = 0;
    idx = heart.second + 1;
    while (idx < n && frame[heart.first][idx++] == '*') r_arm++;

    int waist = 0;
    idx = heart.first + 1;
    while (idx < n && frame[idx++][heart.second] == '*') waist++;

    int l_leg = 0;
    idx = heart.first + waist + 1;
    while (idx < n && frame[idx++][heart.second - 1] == '*') l_leg++;

    int r_leg = 0;
    idx = heart.first + waist + 1;
    while (idx < n && frame[idx++][heart.second + 1] == '*') r_leg++;

    cout << heart.first + 1 << ' ' 
        << heart.second + 1 << '\n';
    cout << l_arm << ' '
        << r_arm << ' '
        << waist << ' '
        << l_leg << ' '
        << r_leg << '\n';

    return EXIT_SUCCESS;
}