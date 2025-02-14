#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MOD 1000000007

vector<int> arr;

struct lazy_node {
    bool set_first;
    long long add_val, mul_val, set_val;
};

struct seg_tree {
    vector<long long> tree;
    vector<lazy_node> lazy;

    seg_tree(int size) {
        tree.resize(4 * size);
        lazy.resize(4 * size, { false, 0, 1, 0 });
        build(1, 0, size - 1);
    }

    void build(int cur, int start, int end) {
        if (start == end) {
            tree[cur] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);
        tree[cur] = (tree[cur * 2] + tree[cur * 2 + 1]) % MOD;
    }

    void update_lazy(int cur, int start, int end) {
        int mid = (start + end) / 2, left = cur * 2, right = cur * 2 + 1;
        if (lazy[cur].set_first) {
            lazy[left].set_first = true;
            lazy[left].set_val = lazy[cur].set_val;
            lazy[left].mul_val = 1;
            lazy[left].add_val = 0;
            tree[left] = (lazy[cur].set_val * (mid - start + 1)) % MOD;

            lazy[right].set_first = true;
            lazy[right].set_val = lazy[cur].set_val;
            lazy[right].mul_val = 1;
            lazy[right].add_val = 0;
            tree[right] = (lazy[cur].set_val * (end - mid)) % MOD;

            lazy[cur].set_first = false;
            lazy[cur].mul_val = 1;
            lazy[cur].add_val = 0;
        }

        if(lazy[cur].mul_val != 1 || lazy[cur].add_val != 0) {
            if(lazy[left].set_first) {
                lazy[left].set_val = (lazy[left].set_val * lazy[cur].mul_val + lazy[cur].add_val) % MOD;
                tree[left] = (lazy[left].set_val * (mid - start + 1)) % MOD;
            } else {
                lazy[left].mul_val = (lazy[left].mul_val * lazy[cur].mul_val) % MOD;
                lazy[left].add_val = (lazy[left].add_val * lazy[cur].mul_val + lazy[cur].add_val) % MOD;
                tree[left] = (tree[left] * lazy[cur].mul_val + lazy[cur].add_val * (mid - start + 1)) % MOD;
            }

            // 오른쪽 자식
            if(lazy[right].set_first) {
                lazy[right].set_val = (lazy[right].set_val * lazy[cur].mul_val + lazy[cur].add_val) % MOD;
                tree[right] = (lazy[right].set_val * (end - mid)) % MOD;
            } else {
                lazy[right].mul_val = (lazy[right].mul_val * lazy[cur].mul_val) % MOD;
                lazy[right].add_val = (lazy[right].add_val * lazy[cur].mul_val + lazy[cur].add_val) % MOD;
                tree[right] = (tree[right] * lazy[cur].mul_val + lazy[cur].add_val * (end - mid)) % MOD;
            }

            lazy[cur].mul_val = 1;
            lazy[cur].add_val = 0;
        }
    }

    void update_range(int cur, int start, int end, int left, int right, int type, long long value) {
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            if (type == 3) {
                lazy[cur].set_first = true;
                lazy[cur].set_val = value % MOD;
                lazy[cur].add_val = 0;
                lazy[cur].mul_val = 1;
                tree[cur] = (value % MOD) * (end - start + 1) % MOD;
            } else if (type == 2) {
                if (lazy[cur].set_first) {
                    lazy[cur].set_val = lazy[cur].set_val * value % MOD;
                    tree[cur] = lazy[cur].set_val * (end - start + 1) % MOD;
                } else {
                    lazy[cur].mul_val = lazy[cur].mul_val * value % MOD;
                    lazy[cur].add_val = lazy[cur].add_val * value % MOD;
                    tree[cur] = tree[cur] * value % MOD;
                }
            } else {
                if (lazy[cur].set_first) {
                    lazy[cur].set_val = (lazy[cur].set_val + value) % MOD;
                    tree[cur] = lazy[cur].set_val * (end - start + 1) % MOD;
                } else {
                    lazy[cur].add_val = (lazy[cur].add_val + value) % MOD;
                    tree[cur] = (tree[cur] + (value % MOD) * (end - start + 1)) % MOD;
                }
            }
            return;
        }
        update_lazy(cur, start, end);
        int mid = (start + end) / 2;
        update_range(cur * 2, start, mid, left, right, type, value);
        update_range(cur * 2 + 1, mid + 1, end, left, right, type, value);
        tree[cur] = (tree[cur * 2] + tree[cur * 2 + 1]) % MOD;
    }

    long long query_range(int cur, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        update_lazy(cur, start, end);
        int mid = (start + end) / 2;
        return (query_range(cur * 2, start, mid, left, right) +
                query_range(cur * 2 + 1, mid + 1, end, left, right)) % MOD;
    }
};

int main() {
    FAST_IO

    int n; cin >> n;
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    seg_tree st(n);
    int m; cin >> m;
    while (m--) {
        int q, x, y; cin >> q >> x >> y;
        x--; y--;
        if (q == 4) {
            cout << st.query_range(1, 0, n - 1, x, y) << '\n';
        } else {
            long long v; cin >> v;
            st.update_range(1, 0, n - 1, x, y, q, v);
        }
    }

    return EXIT_SUCCESS;
}