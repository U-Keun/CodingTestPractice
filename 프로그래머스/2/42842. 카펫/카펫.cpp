#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    for (int i = yellow; i >= 0; i--) {
        if (yellow % i != 0) continue;
        int w = i, h = yellow / i;
        if ((2 * (w + h) + 4) == brown) return { w + 2, h + 2 };
    }
    return { -1, -1 };
}