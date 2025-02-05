#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

typedef long long ll;

vector<ll> tree, lazy;

ll generate(const vector<ll>& arr, int current, int start, int end) {
    if(start == end)
        return tree[current] = arr[start];
    int mid = (start + end) / 2;
    return tree[current] = generate(arr, current * 2, start, mid)
                          + generate(arr, current * 2 + 1, mid + 1, end);
}

void updateLazy(int current, int start, int end) {
    if(lazy[current] != 0) {
        tree[current] += (ll)(end - start + 1) * lazy[current];
        if(start != end) { // 내부 노드인 경우 자식에게 lazy 값 전파
            lazy[current * 2] += lazy[current];
            lazy[current * 2 + 1] += lazy[current];
        }
        lazy[current] = 0;
    }
}

ll prefixSum(int current, int start, int end, int left, int right) {
    updateLazy(current, start, end);
    if(right < start || left > end)
        return 0;
    if(left <= start && end <= right)
        return tree[current];
    int mid = (start + end) / 2;
    return prefixSum(current * 2, start, mid, left, right)
           + prefixSum(current * 2 + 1, mid + 1, end, left, right);
}

void update(int current, int start, int end, int left, int right, ll diff) {
    updateLazy(current, start, end);
    if(right < start || left > end)
        return;
    if(left <= start && end <= right) {
        tree[current] += (ll)(end - start + 1) * diff;
        if(start != end) {
            lazy[current * 2] += diff;
            lazy[current * 2 + 1] += diff;
        }
        return;
    }
    int mid = (start + end) / 2;
    update(current * 2, start, mid, left, right, diff);
    update(current * 2 + 1, mid + 1, end, left, right, diff);
    tree[current] = tree[current * 2] + tree[current * 2 + 1];
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M, K;
    cin >> N >> M >> K;
    vector<ll> arr(N);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    // 트리 높이 계산 및 트리 길이 설정 (1-indexed)
    int h = (int)ceil(log2(N));
    int treeLength = 1 << (h + 1);
    tree.assign(treeLength, 0);
    lazy.assign(treeLength, 0);

    // 구간 합 세그먼트 트리 생성
    generate(arr, 1, 0, N - 1);

    // M번의 업데이트와 K번의 쿼리를 총 M+K번 처리
    while(M + K > 0) {
        int a, b, c;
        cin >> a >> b >> c;
        if(a == 1) {
            ll d;
            cin >> d;
            // b와 c는 구간의 시작과 끝 (1-indexed 입력 -> 0-indexed 내부 처리)
            update(1, 0, N - 1, b - 1, c - 1, d);
            M--;
        } else { // a == 2
            cout << prefixSum(1, 0, N - 1, b - 1, c - 1) << "\n";
            K--;
        }
    }
    return 0;
}