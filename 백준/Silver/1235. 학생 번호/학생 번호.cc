#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<string> input(n);
    REP(i, 0, n - 1) {
        cin >> input[i];
    }

    int l = input[0].size(), answer = 1;
    while (true) {
        unordered_map<string, int> tmp;
        bool flag = false;
        for (string num : input) {
            if (tmp.find(num.substr(l - answer)) == tmp.end()) {
                tmp[num.substr(l - answer)];
            } else {
                flag = true;
                break;
            }
        }

        if (flag) {
            answer++;
            continue;
        }

        break;
    }

    cout << answer;

    return 0;
}