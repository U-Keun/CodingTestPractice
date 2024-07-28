#include <iostream>
#include <vector>
#include <cmath>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);

using namespace std;

class SegmentTree {
    vector<pair<int, int> > tree;
    vector<int> array;
    int arrLength;
    pair<int, int>& generate(int, int, int);
    pair<int, int> query(int, int, int, int, int);
    void update(int, int, int, int, int);
    pair<int, int> updateChoice(pair<int, int>&, pair<int, int>&) const;
public:
    explicit SegmentTree(const vector<int>&);
    int query(int, int);
    void update(int, int);
};

SegmentTree::SegmentTree(const vector<int>& arr) {
    this->array = arr;
    this->arrLength = arr.size();
    const int h = static_cast<int>(ceil(log2(arrLength)));
    tree.resize(2 << h);
    generate(1, 0, arrLength - 1);
}

pair<int, int>& SegmentTree::generate(const int current, const int start, const int end) {
    if (start == end) {
        this->tree[current].first = start;
        this->tree[current].second = -1;
        return this->tree[current];
    }
    const int mid = (start + end) / 2;
    return this->tree[current] =
        updateChoice(generate(current * 2, start, mid),
                    generate(current * 2 + 1, mid + 1, end));
}

pair<int, int> SegmentTree::updateChoice(pair<int, int>& left, pair<int, int>& right) const {
    if (left.first < 0) return right;
    if (right.first < 0) return left;

    pair<int, int> tmp = left;
    if (left.first == right.first) {
        if (left.second < 0) { tmp.second = right.second; return tmp; }
        if (right.second < 0) { tmp.second = left.second; return tmp; }

        if (array[left.second] > array[right.second]) tmp.second = left.second;
        else tmp.second = right.second;
    } else {
        if (array[left.first] == array[right.first]) {
            tmp.second = right.first;
        } else if (array[left.first] > array[right.first]) {
            if (left.second < 0 || array[left.second] < array[right.first]) tmp.second = right.first;
        } else {
            tmp.first = right.first;
            if (right.second < 0 || array[left.first] > array[right.second]) tmp.second = left.first;
            else tmp.second = right.second;
        }
    }
    return tmp;
}

pair<int, int> SegmentTree::query(const int current, const int start, const int end, const int left, const int right) {
    pair<int, int> tmp;
    tmp.first = -1;
    tmp.second = -1;
    if (left > end || right < start) {
        return tmp;
    }
    if (left <= start && right >= end) return this->tree[current];
    const int mid = (start + end) / 2;
    pair<int, int> leftHalf = query(current * 2, start, mid, left, right);
    pair<int, int> rightHalf = query(current * 2 + 1, mid + 1, end, left, right);
    tmp = updateChoice(leftHalf, rightHalf);
    return tmp;
}

void SegmentTree::update(const int current, const int start, const int end, const int idx, const int value) {
    if (idx < start || idx > end || start == end) return;
    if (start != end) {
        const int mid = (start + end) / 2;
        update(current * 2, start, mid, idx, value);
        update(current * 2 + 1, mid + 1, end, idx, value);
        this->tree[current] = updateChoice(tree[current * 2], tree[current * 2 + 1]);
    }
}

int SegmentTree::query(const int left, const int right) {
    const pair<int, int> choice = query(1, 0, this->arrLength - 1, left - 1, right - 1);
    return this->array[choice.first] + this->array[choice.second];
}


void SegmentTree::update(const int idx, const int value) {
    this->array[idx - 1] = value;
    update(1, 0, this->arrLength - 1, idx - 1, value);
}


int main() {
    FAST_IO();

    int n;
    cin >> n;

    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    SegmentTree segment_tree(arr);

    int m;
    cin >> m;
    int q, a, b;
    for (int i = 0; i < m; i++) {
        cin >> q >> a >> b;
        if (q == 1) segment_tree.update(a, b);
        else cout << segment_tree.query(a, b) << '\n';
    }

    return 0;
}