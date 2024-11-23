#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<int> parents;

int find_parent(int x) {
    if (parents[x] == x) return x;
    return parents[x] = find_parent(parents[x]);
}

bool union_tree(int x, int y) {
    x = find_parent(x);
    y = find_parent(y);

    if (x == y) return false;

    if (x < y) parents[y] = x;
    else parents[x] = y;

    return true;
}

int main() {
    FAST_IO

    int v, e;
    cin >> v >> e;
    parents.resize(v + 1);
    REP(i, 1, v) parents[i] = i;

    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> pq;
    int a, b, c;
    REP(i, 1, e) {
        cin >> a >> b >> c;
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        pq.emplace(c, a, b);
    }

    int count = 1, answer = 0;
    while (count < v) {
        int w = get<0>(pq.top()),
            u = get<1>(pq.top()),
            v = get<2>(pq.top());
        pq.pop();

        if (union_tree(u, v)) {
            count++;
            answer += w;
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}