#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

unordered_map<int, char> tree;

string decode(const string& word, int size) {
    string ans;
    int idx = 0, cur = 1;
    while (idx <= size) {
        if (tree.count(cur) && tree[cur] != '*') {
            ans += tree[cur];
            cur = 1;
        } else {
            if (idx == size) break;
            cur = word[idx++] == '0' ? cur * 2 : cur * 2 + 1;
        }
    }
    return ans;
}

int main() {
    FAST_IO;

    int t, w; cin >> t;
    while (t--) {
        cin >> w; string nodes;
        cin >> nodes;
        tree.clear();

        int l = (int) nodes.size();
        for (int i = 1; i <= l; ++i)
            if (nodes[i - 1] != '*') tree[i] = nodes[i - 1];

        while (w--) {
            string word; cin >> word;
            int size = (int) word.size();
            cout << decode(word, size) << ' ';
        }
        cout << '\n';
    }

    return EXIT_SUCCESS;
}