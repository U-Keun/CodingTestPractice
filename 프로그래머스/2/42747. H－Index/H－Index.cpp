#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    sort(citations.begin(), citations.end());
    int n = citations.size();
    for (int i = 0; i < n; i++) {
        if (citations[i] >= n - i) return n - i;
    }
    return 0;
}