#include <iostream>
#include <vector>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

void getTernary(long long num, vector<int>& res) {
    if (num < 3) {
        res.push_back(num);
        return;
    }

    getTernary(num / 3, res);
    res.push_back(num % 3);
}

int main() {
    FAST_IO

    int t;
    cin >> t;

    long long input;
    while (t-- > 0) {
        cin >> input;
        bool isMinus = false;
        if (input < 0) {
            isMinus = true;
            input *= -1;
        }

        vector<int> res;
        getTernary(input, res);

        int idx = res.size() - 1;
        stack<int> ans;
        while (idx > 0) {
            if (res[idx] == 3) {
                ans.push(0);
                res[idx - 1]++;
            } else if (res[idx] == 2) {
                ans.push(-1);
                res[idx - 1] += res[idx] - 1;
            } else {
                ans.push(res[idx]);
            }
            idx--;
        }

        if (res[0] == 3) { //
            if (isMinus) cout << "-0";
            else cout << "10";
        } else if (res[0] == 2) {
            if (isMinus) cout << "-1";
            else cout << "1-";
        } else {
            if (isMinus) {
                if (res[0] == 1) cout << '-';
                else cout << '1';
            }
            else cout << res[0];
        }

        while (!ans.empty()) {
            if ((isMinus && ans.top() == 1) || !isMinus && ans.top() == -1) cout << '-';
            else if ((isMinus && ans.top() == -1) || !isMinus && ans.top() == 1) cout << '1';
            else cout << ans.top();
            ans.pop();
        }
        cout << '\n';
    }

    return 0;
}