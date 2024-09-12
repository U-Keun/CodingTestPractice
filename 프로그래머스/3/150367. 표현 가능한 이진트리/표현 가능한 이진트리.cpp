#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

void convertToBit(vector<bool>& bit, long long number) {
    if (number == 0) return;
    
    int val = number % 2;
    convertToBit(bit, number / 2);
    bit.push_back(val);
}

bool divideConquer(vector<bool>& bit, int start, int end) {
    if (start >= end) return true;
    
    bool isAllZero = true;
    REP(i, start, end) {
        if (bit[i]) {
            isAllZero = false;
            break;
        }
    }
    
    if (isAllZero) return true;
    
    int mid = (start + end) / 2;
    
    if (!bit[mid]) return false;
    
    return divideConquer(bit, start, mid - 1) && divideConquer(bit, mid + 1, end);
}

vector<int> solution(vector<long long> numbers) {
    vector<int> answer;
    
    for (long long number : numbers) {
        vector<bool> bit;
        convertToBit(bit, number);
        
        int l = bit.size(), k = 0;
        while ((1 << k) - 1 < l) k++;
        int targetSize = (1 << k) - 1;
        int padding = targetSize - l;

        vector<bool> paddedBit(padding, 0);
        paddedBit.insert(paddedBit.end(), bit.begin(), bit.end());
       
        bool result = divideConquer(paddedBit, 0, paddedBit.size() - 1);
        answer.push_back(result);
    }
    
    return answer;
}