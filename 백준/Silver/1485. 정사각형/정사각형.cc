#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int t;
    cin >> t;
    while (t-- > 0) {
        vector<pair<int, int>> points;
        for (int i = 0; i < 4; ++i) {
            int x, y;
            cin >> x >> y;
            points.push_back({x, y});
        }

        sort(points.begin(), points.end());

        int c1x = (points[0].first + points[3].first);
        int c1y = (points[0].second + points[3].second);
        int c2x = (points[1].first + points[2].first);
        int c2y = (points[1].second + points[2].second);

        if (c1x != c2x || c1y != c2y) {
            cout << "0" << '\n';
            continue;
        }

        int dx1 = points[3].first - points[0].first;
        int dx2 = points[2].first - points[1].first;
        int dy1 = points[3].second - points[0].second;
        int dy2 = points[2].second - points[1].second;

        long long tangentCheck = dy1 * dy2 + dx2 * dx1;
        if (tangentCheck != 0 ||
            (dx1 * dx1 + dy1 * dy1 != dx2 * dx2 + dy2 * dy2)) {
            cout << "0" << '\n';
            continue;
        }
        
        cout << "1" << '\n';
        points.clear();
    }
    return 0;
}