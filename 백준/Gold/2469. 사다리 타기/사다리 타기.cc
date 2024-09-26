#include <iostream>
#include <vector>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int k, n;
    string target;
    cin >> k >> n >> target;
    vector<int> result;
    for (char c : target) result.push_back(c - 'A');

    vector<int> participants;
    stack<string> lowerRows;
    REP(i, 0, k - 1) participants.push_back(i);
    string row;
    bool isUpper = true;
    REP(i, 0, n - 1) {
        cin >> row;
        if (row[0] == '?') {
            isUpper = false;
            continue;
        }

        if (isUpper) {
            REP(j, 0, k - 2) {
                if (row[j] == '-') {
                    int tmp = participants[j];
                    participants[j] = participants[j + 1];
                    participants[j + 1] = tmp;
                }
            }
        } else {
            lowerRows.push(row);
        }
    }

    while (!lowerRows.empty()) {
        string cur = lowerRows.top();
        lowerRows.pop();
        REP(j, 0, k - 2) {
            if (cur[j] == '-') {
                int tmp = result[j];
                result[j] = result[j + 1];
                result[j + 1] = tmp;
            }
        }
    }

    string answer = "";

    bool prevBridge = false;
    REP(i, 0, k - 2) {
        if (prevBridge) {
            answer += '*';
            prevBridge = false;
        } else if (participants[i] == result[i]) {
            answer += '*';
        } else if (participants[i] == result[i + 1] && participants[i + 1] == result[i]) {
            answer += '-';
            prevBridge = true;
        } else {
            REP(j, 0, k - 2) cout << 'x';
            return 0;
        }
    }

    cout << answer;

    return 0;
}