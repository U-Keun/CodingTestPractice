#include <string>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

bool isValidPth(queue<char> charQueue) {
    stack<char> stack;
    while (!charQueue.empty()) {
        char cur = charQueue.front();
        charQueue.pop();
        if (cur == '(' || cur == '{' || cur == '[') {
                stack.push(cur);
                continue;
        }
        
        if (stack.empty()) {
            return false;
        }
        
        if ((stack.top() == '(' && cur == ')') ||
            (stack.top() == '{' && cur == '}') ||
            (stack.top() == '[' && cur == ']')) stack.pop();
        else return false;
    }
    
    return stack.empty();
}

int solution(string s) {
    queue<char> charQueue;
    for (char c : s) charQueue.push(c);
    
    int l = s.size(), answer = 0;;
    for (int i = 0; i < l; i++) {
        if (isValidPth(charQueue)) answer++;
        charQueue.push(charQueue.front());
        charQueue.pop();
    }
    
    return answer;
}