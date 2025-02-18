#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

struct seg_tree {
    int n;
    vector<long long> tree;

    seg_tree(int size) {
        n = size;
        tree.resize(4 * size);
    }

    void update_point(int cur, int start, int end, int idx, long long val) {
        if (idx < start || end < idx) return;
        if (start == end) {
            tree[cur] = val;
            return;
        }
        int mid = (start + end) / 2;
        update_point(cur * 2, start, mid, idx, val);
        update_point(cur * 2 + 1, mid + 1, end, idx, val);
        tree[cur] = max(tree[cur * 2], tree[cur * 2 + 1]);
    }

    void update(int idx, long long val) {
        update_point(1, 0, n - 1, idx, val);
    }

    long long query_range(int cur, int start, int end, int left, int right) {
        if (right < start || left > end) return INT64_MIN;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return max(query_range(cur * 2, start, mid, left, right),
                query_range(cur * 2 + 1, mid + 1, end, left, right));
    }

    long long query(int left, int right) {
        return query_range(1, 0, n - 1, left, right);
    }
};

int main() {
    FAST_IO

    int n, d; cin >> n >> d;
    vector<int> numbers(n);
    REP(i, 0, n - 1) cin >> numbers[i];

    vector<long long> dp(n);
    seg_tree st(n);

    long long answer = INT64_MIN;
    REP(i, 0, n - 1) {
        long long prev = st.query(max(0, i - d), i - 1);
        if (prev < 0) dp[i] = numbers[i];
        else dp[i] = numbers[i] + prev;

        st.update(i, dp[i]);
        answer = max(answer, dp[i]);
    }

    cout << answer;

    return EXIT_SUCCESS;
}