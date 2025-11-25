#include <iostream>
#include <string>
#include <stack>

using namespace std;

int solution(string s)
{
    stack<char> st;
    for (char c : s) {
        if (st.empty() || st.top() != c) st.push(c);
        else if (st.top() == c) st.pop();
    }

    return st.empty();
}