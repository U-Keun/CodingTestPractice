#include <iostream>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int T, N;
    cin >> T >> N;
    map<string, int> day = {
            { "Mon", 0 },
            { "Tue", 1 },
            { "Wed", 2 },
            { "Thu", 3 },
            { "Fri", 4 }
    };
    for (int i = 0; i < N; i++) {
        string start, end;
        int startTime, endTime;
        cin >> start >> startTime >> end >> endTime;
        T -= endTime - startTime + 24 * (day[end] - day[start]);
    }

    if (T > 48) cout << -1;
    else if (T <= 0) cout << 0;
    else cout << T;

    return 0;
}