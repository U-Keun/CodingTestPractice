#include <string>
#include <vector>
#include <stack>
#include <unordered_map>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    
    stack<char> st;
    st.push('{');
    
    vector<int> numbers;
    int idx = 1, number = 0;
    unordered_map<int, int> tuple;
    while (!st.empty() && idx < s.size()) {
        switch (s[idx]) {
            case '{':
                numbers.clear();
            	number = 0;
            	st.push(s[idx]);
                break;
            case '}':
                if (st.size() == 2) {
                	numbers.push_back(number);
                	for (int n : numbers) {
                    	if (tuple.find(n) == tuple.end()) {
                        	tuple[n] = 1;
                    	} else {
                        	tuple[n] = tuple[n] + 1;
                    	}
             		}
            	    
            	}
            	st.pop();
                break;
            case ',':
                if (st.size() == 2) {
                	numbers.push_back(number);
                	number = 0;
            	}
                break;
            default:
                number *= 10;
        		number += s[idx] - '0';
                break;
        }
        idx++;
    }
    
    answer.resize(tuple.size());
    for (const auto& elt : tuple) {
        answer[tuple.size() - elt.second] = elt.first;
    }
    
    return answer;
}