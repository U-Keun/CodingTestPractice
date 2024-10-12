#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int rsp(int a, int b) {
   if (a == b) return 0;
   if (a < b) {
       if (a == 1 && b == 3) return -1;
       return 1;
   } else {
       if (b == 1 && a == 3) return 1;
       return -1;
   }
}

int main() {
    FAST_IO

    int n;
    cin >> n;
    vector<short> A(n), B(n);
    REP(i, 0, n - 1) cin >> A[i];
    REP(i, 0, n - 1) cin >> B[i];

    int answer = 0, curA = 0, curB = 0;
    REP(i, 0, n - 1) {
        int res = rsp(A[i], B[i]);
        if (res == -1) {
            curA++;
            answer = max(answer, curB);
            curB = 0;
        } else if (res == 1) {
            curB++;
            answer = max(answer, curA);
            curA = 0;
        } else {
            if (curA == 0) {
                curA++;
                answer = max(answer, curB);
                curB = 0;
            } else {
                curB++;
                answer = max(answer, curA);
                curA = 0;
            }
        }
    }
    answer = max(answer, curA);
    answer = max(answer, curB);

    cout << answer;

    return 0;
}