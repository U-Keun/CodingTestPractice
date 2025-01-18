#include <iostream>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n; cin >> n;
    stack<pair<int, int>> st;

    int num;
    long long answer = 0;
    while (n--) {
        cin >> num;
        if (st.empty()) {
            st.push({ num, 1 });
            continue;
        }

        while (!st.empty() && st.top().first < num) {
            answer += st.top().second;
            st.pop();
        }

        if (st.empty()) {
            st.push({ num, 1 });
            continue;
        }

        if (st.top().first == num) {
            long long count = st.top().second;
            st.pop();

            answer += count;
            if (!st.empty()) answer++;

            st.push({num, count + 1});
        } else {
            answer++;
            st.push({ num, 1 });
        }
    }

    cout << answer;

   return EXIT_SUCCESS;
}