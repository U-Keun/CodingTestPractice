#include <iostream>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;
    unordered_map<string, int> workingTime;
    REP_INC(i, 0, n - 1) {
        string name;
        REP_INC(i, 0, 6) {
            cin >> name;
            if (name == "-") continue;

            if (workingTime.find(name) == workingTime.end()) {
                workingTime[name] = 4;
            } else {
                workingTime[name] += 4;
            }
        }

        REP_INC(i, 0, 6) {
            cin >> name;
            if (name == "-") continue;

            if (workingTime.find(name) == workingTime.end()) {
                workingTime[name] = 6;
            } else {
                workingTime[name] += 6;
            }
        }
        REP_INC(i, 0, 6) {
            cin >> name;
            if (name == "-") continue;

            if (workingTime.find(name) == workingTime.end()) {
                workingTime[name] = 4;
            } else {
                workingTime[name] += 4;
            }
        }
        REP_INC(i, 0, 6) {
            cin >> name;
            if (name == "-") continue;

            if (workingTime.find(name) == workingTime.end()) {
                workingTime[name] = 10;
            } else {
                workingTime[name] += 10;
            }
        }
    }

    int min = 10000000, max = 0;
    for (auto elt : workingTime) {
        if (elt.second < min) min = elt.second;
        if (elt.second > max) max = elt.second;
    }

    if (max - min > 12) cout << "No\n";
    else cout << "Yes\n";

    return 0;
}