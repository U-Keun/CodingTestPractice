#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

#define ll long long

class SegmentTree {
    vector<ll> tree;
    vector<ll> array;
    int arrLength;
    ll generate(int current, int start, int end, vector<ll>& arr);
    ll query(int current, int start, int end, int left, int right);
    void update(int current, int start, int end, int idx, ll value);
public:
    SegmentTree(vector<ll>& arr);
    ll query(int left, int right);
    void update(int idx, ll value);
};

SegmentTree::SegmentTree(vector<long long>& arr) {
    array = arr;
    arrLength = arr.size();
    int h = (int) ceil(log2(arrLength));
    tree.resize(2 << h);
    generate(1, 0, arrLength - 1, arr);
}

ll SegmentTree::generate(int current, int start, int end, vector<long long>& arr) {
    if (start == end) return this->tree[current] = arr[start];;
    int mid = (start + end) >> 1;
    return this->tree[current] =
        generate(current * 2, start, mid, arr) +
        generate(current * 2 + 1, mid + 1, end, arr);
}

ll SegmentTree::query(int current, int start, int end, int left, int right) {
    if (left > end || right < start) return 0;
    if (left <= start && right >= end) return this->tree[current];
    int mid = (start + end) >> 1;
    return query(current * 2, start, mid, left, right) +
        query(current * 2 + 1, mid + 1, end, left, right);
}

void SegmentTree::update(int current, int start, int end, int idx, long long value) {
    if (idx < start || idx > end) return;
    tree[current] += value;
    if (start != end) {
        int mid = (start + end) >> 1;
        update(current * 2, start, mid, idx, value);
        update(current * 2 + 1, mid + 1, end, idx, value);
    }
}

ll SegmentTree::query(int left, int right) {
    return query(1, 0, arrLength - 1, left - 1, right - 1);
}

void SegmentTree::update(int idx, long long value) {
    update(1, 0, arrLength - 1, idx - 1, value - array[idx - 1]);
    array[idx - 1] = value;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;
    cin >> n >> m >> k;

    vector<ll> arr(n);
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }

    SegmentTree obj(arr);

    ll q, a, b;
    while (m + k > 0) {
        cin >> q >> a >> b;
        if (q == 1) {
            obj.update(a, b);
            m--;
        } else {
            cout << obj.query(a, b) << '\n';
            k--;
        }
    }

    return 0;
}