#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<int> elements) {
    map<int, int> rec;
    int n = elements.size();
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < n; j++) {
            int cur = 0;
            for (int k = j; k < j + i; k++) cur += elements[k % n];
            rec[cur]++;
        }
    }
    return rec.size();
}