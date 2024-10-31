#include <iostream>
#include <string>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    string A, B;
    cin >> A >> B;

    queue<int> q;
    REP(i, 0, 7) {
        q.push(A[i] - '0');
        q.push(B[i] - '0');
    }

    int l = q.size();
    while (l > 2) {
        REP(i, 1, l - 1) {
            int tmp = q.front();
            q.pop();
            int val = tmp + q.front();
            q.push(val % 10);
        }
        q.pop();

        l = q.size();
    }

    cout << q.front();
    q.pop();
    cout << q.front();
    q.pop();

    return 0;
}