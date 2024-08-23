#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct cmp {
    bool operator() (pair<int, int> a, pair<int, int> b) {
        if (a.first == b.first) return a.second > b.second;
        return a.first < b.first;
    }
};

int main() {
// int algorithm() {
    FAST_IO

    int a, b;
    cin >> a;

    priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> A;
    for (int i = 0; i < a; i++) {
        int val;
        cin >> val;
        A.push(make_pair(val, i));
    }

    cin >> b;
    priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> B;
    for (int i = 0; i < b; i++) {
        int val;
        cin >> val;
        B.push(make_pair(val, i));
    }

    vector<int> answer;
    int AIdx = -1, BIdx = -1;
    while (!A.empty() && !B.empty()) {
        if (A.top().first > B.top().first) {
            A.pop();
        } else if (A.top().first < B.top().first) {
            B.pop();
        } else {
            if (A.top().second > AIdx && B.top().second > BIdx) {
                answer.emplace_back(A.top().first);
                AIdx = A.top().second;
                BIdx = B.top().second;
                A.pop();
                B.pop();
                continue;
            }

            if (A.top().second < AIdx) A.pop();
            if (B.top().second < BIdx) B.pop();
        }
    }

    cout << answer.size() << '\n';
    for (int num : answer) {
        cout << num << ' ';
    }

    return 0;
}