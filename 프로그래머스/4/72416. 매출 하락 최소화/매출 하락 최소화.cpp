#include <string>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int calcMin(int current, 
            bool isContained, 
            vector<vector<int>>& graph, 
            vector<vector<int>>& dp, 
            vector<int>& sales) {
    if (dp[current][isContained] != -1) return dp[current][isContained];
    
    if (graph[current].size() == 0) {
        return isContained ? sales[current] : 0;
    }
    
    int minDiff = INT_MAX, 
    	childrenCnt = graph[current].size(), 
    	value = isContained ? sales[current] : 0;
    bool chk = false;
    for (int i = 0; i < childrenCnt; i++) {
        int containCrt = calcMin(graph[current][i], true, graph, dp, sales),
        	notContainCrt = calcMin(graph[current][i], false, graph, dp, sales);
        minDiff = min(minDiff, containCrt - notContainCrt);
        if (containCrt > notContainCrt) {
            value += notContainCrt;
        } else {
            value += containCrt;
            chk = true;
        }
    }
    
    if (!isContained && !chk) value += minDiff;
    dp[current][isContained] = value;
    return value;
}

int solution(vector<int> sales, vector<vector<int>> links) {    
    int n = sales.size();
    vector<vector<int>> dp(n, vector<int>(2, -1));
    
    vector<vector<int>> graph(n, vector<int>(0));
    for (vector<int> link : links) {
        graph[link[0] - 1].emplace_back(link[1] - 1);
    }
    
    return min(calcMin(0, false, graph, dp, sales), 
               calcMin(0, true, graph, dp, sales));
}