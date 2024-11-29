#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

class union_find {
    vector<int> parents;

public:
    union_find(int size) : parents(size + 1) {
        REP(i, 1, size) parents[i] = i;
    }

    int find_parent(int x) {
        if (x != parents[x]) parents[x] = find_parent(parents[x]);
        return parents[x];
    }

    void union_set(int x, int y) {
        x = find_parent(x);
        y = find_parent(y);
        if (x != y) parents[x] = y;
    }
};

int main() {
    FAST_IO

    int g, p;
    cin >> g >> p;
    union_find uf(g);

    vector<int> input(p);
    REP(i, 0, p - 1) cin >> input[i];

    int answer = 0;
    REP(i, 0, p - 1) {
        int available = uf.find_parent(input[i]);
        if (available == 0) break;
        answer++;
        uf.union_set(available, available - 1);
    }

    cout << answer;

    return EXIT_SUCCESS;
}