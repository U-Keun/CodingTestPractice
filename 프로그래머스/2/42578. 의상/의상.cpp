#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    unordered_map<string, int> count;
    for (auto& v : clothes) {
        count[v[1]]++;
    }
    
    int answer = 1;
    for (auto& e : count) {
        answer *= e.second + 1;
    }
    return answer - 1;
}