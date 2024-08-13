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
    
    if (graph[current].empty()) {
        return dp[current][isContained] = (isContained ? sales[current] : 0);
    }
    
    int minDiff = INT_MAX, total = 0; 
    bool mustIncludeChild = false;
   
    for (int child : graph[current]) {
        int includeChild = calcMin(child, true, graph, dp, sales), 
        	excludeChild = calcMin(child, false, graph, dp, sales);

        if (includeChild > excludeChild) {
            total += excludeChild;
            minDiff = min(minDiff, includeChild - excludeChild);
        } else {
            total += includeChild;
            mustIncludeChild = true;
        }
    }
    
    if (!isContained && !mustIncludeChild) total += minDiff;
    return dp[current][isContained] = total + (isContained ? sales[current] : 0);
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
