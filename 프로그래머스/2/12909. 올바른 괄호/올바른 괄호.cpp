#include<string>
#include <stack>

using namespace std;

bool solution(string s)
{
    stack<int> st;
    for (char c : s) {
        if (c == '(') {
            st.push(c);
        } else {
            if (st.size() == 0) return false;
            st.pop();
        }
    }

    return st.size() == 0;
}