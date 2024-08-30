#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int solution(vector<string> arr)
{
    int l = arr.size();
    
    vector<int> numbers;
    vector<string> operations;
    for (int i = 0; i < l; i++) {
        if (i % 2 == 0) numbers.emplace_back(stoi(arr[i]));
        else operations.emplace_back(arr[i]);
    }
    
    int n = l / 2 + 1;
    vector<vector<int>> maxDp(n, vector<int>(n, -1000000000)),
    	minDp(n, vector<int>(n, 1000000000));
    
    for (int i = 0; i < n; i++) {
        maxDp[i][i] = numbers[i];
        minDp[i][i] = numbers[i];
    }
    
    for (int m = 2; m <= n; m++) {
        for (int i = 0; i < n - m + 1; i++) {
            int j = i + m - 1;
            for (int k = i; k < j; k++) {
                if (operations[k] == "+") {
                    maxDp[i][j] = max(maxDp[i][j], maxDp[i][k] + maxDp[k + 1][j]);
                    minDp[i][j] = min(minDp[i][j], minDp[i][k] + minDp[k + 1][j]);
                } else {
                    maxDp[i][j] = max(maxDp[i][j], maxDp[i][k] - minDp[k + 1][j]);
                    minDp[i][j] = min(minDp[i][j], minDp[i][k] - maxDp[k + 1][j]);
                }
            }
        }
    }
    
    return maxDp[0][n - 1];
}