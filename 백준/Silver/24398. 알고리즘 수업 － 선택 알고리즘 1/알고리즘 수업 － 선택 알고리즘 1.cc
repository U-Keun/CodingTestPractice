#include <bits/stdc++.h>
using namespace std;

int n, q, K;
vector<size_t> A;
bool printed = false;

void do_swap(size_t &x, size_t &y, int &k) {
    swap(x, y);
    if (--k == 0 && !printed) {
        size_t a = x, b = y;
        if (a > b) std::swap(a, b);
        cout << a << " " << b << "\n";
        printed = true;
    }
}

int partition(int p, int r, int &k) {
    size_t pivot = A[r];
    int i = p - 1;
    for (int j = p; j < r && !printed; ++j) {
        if (A[j] <= pivot) {
            do_swap(A[++i], A[j], k);
        }
    }
    if (i + 1 != r && !printed) {
        do_swap(A[i+1], A[r], k);
    }
    return i + 1;
}

void quick_select(int p, int r, int q, int &k) {
    if (printed || p > r) return;
    if (p == r) {
        return;
    }
    int t = partition(p, r, k);
    if (printed) return;
    int left_count = t - p + 1;
    if (q < left_count) {
        quick_select(p, t - 1, q, k);
    } else if (q == left_count) {
        return;
    } else {
        quick_select(t + 1, r, q - left_count, k);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q >> K;
    A.resize(n);
    for (int i = 0; i < n; ++i) {
        cin >> A[i];
    }

    int k = K;
    quick_select(0, n - 1, q, k);

    if (!printed) {
        cout << "-1\n";
    }
    return 0;
}