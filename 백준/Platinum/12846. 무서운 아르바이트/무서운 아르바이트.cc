#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n;
vector<int> arr, seg_tree;

void generate_seg_tree(int cur, int start, int end) {
    if (start == end) {
        seg_tree[cur] = start;
        return;
    }
    int mid = (start + end) / 2;
    generate_seg_tree(cur * 2, start, mid);
    generate_seg_tree(cur * 2 + 1, mid + 1, end);
    int left = seg_tree[cur * 2], right = seg_tree[cur * 2 + 1];
    seg_tree[cur] = (arr[left] <= arr[right]) ? left : right;
}

int query(int cur, int start, int end, int left, int right) {
    if (right < start || left > end) return -1;
    if (left <= start && end <= right) return seg_tree[cur];
    int mid = (start + end) / 2;
    int left_idx = query(cur * 2, start, mid, left, right),
        right_idx = query(cur * 2 + 1, mid + 1, end, left, right);
    if (left_idx == -1) return right_idx;
    if (right_idx == -1) return left_idx;
    return (arr[left_idx] <= arr[right_idx]) ? left_idx : right_idx;
}

long long get_maximum(int left, int right) {
    if (left > right) return 0;
    int min_idx = query(1, 0, n - 1, left, right);

    long long val = arr[min_idx] * (right - left + 1),
        left_max = get_maximum(left, min_idx - 1),
        right_max = get_maximum(min_idx + 1, right);

    return max({val, left_max, right_max});
}

int main() {
    FAST_IO

    cin >> n;
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    seg_tree.resize(4 * n);
    generate_seg_tree(1, 0, n - 1);

    cout << get_maximum(0, n - 1);

    return EXIT_SUCCESS;
}