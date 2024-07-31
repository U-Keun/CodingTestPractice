#include <iostream>
#include <iomanip>
#include <string>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int n, strIdx = 0;
string input;
vector< vector<bool> > decoded;
int x[4] = {0, 0, 1, 1};
int y[4] = {0, 1, 0, 1};

void reccurence(int currentRow,
                int currentCol,
                int copyCount) {
    if (strIdx >= input.size()) return;

    if (input[strIdx] == 'Q') {
        strIdx++;
        for (int i = 0; i < 4; i++) {
            reccurence(currentRow + x[i] * copyCount / 2,
                        currentCol + y[i] * copyCount / 2,
                        copyCount / 2);
        }
    } else {
        if (input[strIdx] == 'B') {
            for (int j = 0; j < copyCount; j++) {
                for (int k = 0; k < copyCount; k++) {
                    decoded[currentRow + j][currentCol + k] = true;
                }
            }
        }
        strIdx++;
    }
}

int main() {
// int algorithm() {
    FAST_IO;

    cin >> n;
    cin >> input;

    decoded.resize(n, vector<bool>(n));

    reccurence(0, 0, n);

    cout << "#define quadtree_width " << n << '\n';
    cout << "#define quadtree_height " << n << '\n';
    cout << "static char quadtree_bits[] = {\n";

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n / 8; j++) {
            int val = 0;
            for (int k = j * 8 + 7; k >= j * 8; k--) {
                if (decoded[i][k]) val++;
                if (k > j * 8) val <<= 1;
            }
            cout << "0x" << hex << setw(2) << setfill('0') << val << ',';
        }
        cout << '\n';
    }
    cout << "};\n";

    return 0;
}