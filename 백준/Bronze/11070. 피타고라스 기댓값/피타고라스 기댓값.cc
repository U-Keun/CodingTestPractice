#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int t; cin >> t;
    while (t-- > 0) {
        int n, m; cin >> n >> m;
        vector<double> point(n), loss(n);
        while (m-- > 0) {
            int a, b, p, q; cin >> a >> b >> p >> q;
            point[a - 1] += p;
            loss[a - 1] += q;
            point[b - 1] += q;
            loss[b - 1] += p;
        }

        int maximum = 0, minimum = 1000;
        for (int i = 0; i < n; i++) {
            double p = point[i], l = loss[i];
            int val;
            if (p == 0 && l == 0) val = 0;
            else val = p * p * 1000 / (p * p + l * l);
            if (maximum < val) maximum = val;
            if (minimum > val) minimum = val;
        }
        cout << maximum << '\n' << minimum << '\n';
    }

    return EXIT_SUCCESS;
}