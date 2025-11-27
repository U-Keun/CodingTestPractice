#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int n = want.size();
    unordered_map<string, int> rec;
    for (int i = 0; i < n; i++) {
        rec[want[i]] = number[i];
    }
    
    int l = discount.size(), answer = 0;
    for (int i = 0; i < l; i++) {
        unordered_map<string, int> tmp;
        for (int j = i; j < min(i + 10, l); j++) {
            tmp[discount[j]]++;
        }
    	
        bool check = true;
        for (string s : want) {
            if (rec[s] > tmp[s]) check = false;
        }
        if (check) answer++;
    }
    
    return answer;
}