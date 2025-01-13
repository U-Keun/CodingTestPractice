#include <iostream>
#include <unordered_map>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

unordered_map<string, string> parent_map;
unordered_map<string, int> size_map;

string find_parent(const string& x) {
    if (parent_map[x] == x) return x;
    return parent_map[x] = find_parent(parent_map[x]);
}

int union_nodes(const string& x, const string& y) {
    string X = find_parent(x), Y = find_parent(y);
    if (X == Y) return size_map[X];

    if (size_map[X] < size_map[Y]) swap(X, Y);
    parent_map[Y] = X;
    size_map[X] += size_map[Y];

    return size_map[X];
}

void add_name(const string& a) {
    if (parent_map.find(a) == parent_map.end()) {
        parent_map[a] = a;
        size_map[a] = 1;
    }
}

int main() {
    FAST_IO

    int t; cin >> t;
    while (t-- > 0) {
        parent_map.clear();
        size_map.clear();

        int f; cin >> f;
        string a, b;
        while (f-- > 0) {
            cin >> a >> b;
            add_name(a);
            add_name(b);
            int val = union_nodes(a, b);
            cout << val << '\n';
        }
    }

    return EXIT_SUCCESS;
}