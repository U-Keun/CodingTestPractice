#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>

using namespace std;

class Node {
public:
    char cur;
    bool eow;
    vector<Node*> children;
    unordered_map<char, int> count;
    Node(char c) {
        this->cur = c;
        this->eow = false;
        this->children.resize(26);
    }
    
    void updateTree(const string& str, int idx, int diff) { // diff : 1 or -1
        if ((diff == 1 && str.size() == idx) || (diff == -1 && idx == -1)) {
            this->eow = true;
            return;
        }
        
        if (this->children[str[idx] - 'a'] == NULL) {
            this->children[str[idx] - 'a'] = new Node(str[idx]);
        }
        
        this->children[str[idx] - 'a']->updateTree(str, idx + diff, diff);
    }
    
    void countCandidates(const string& str, int idx, int diff, int& ans) {
        if ((diff == 1 && str.size() == idx) || (diff == -1 && idx == -1)) {
            if (this->eow) ans++;
            return;
        }
        
        char currentChar = str[idx];
        if (currentChar == '?') {
            for (auto node : this->children) {
                if (node != NULL) node->countCandidates(str, idx + diff, diff, ans);
            }
        } else {
            if (this->children[str[idx] - 'a'] != NULL) {
                this->children[str[idx] - 'a']->countCandidates(str, idx + diff, diff, ans);
            }
        }
    }  
};

vector<int> solution(vector<string> words, vector<string> queries) {
    unordered_map<int, Node*> forward, backward;
    
    for (const string& word : words) {
        int len = word.size();
        if (forward.find(len) == forward.end()) {
            forward[len] = new Node(' ');
            backward[len] = new Node(' ');
        }
        forward[len]->updateTree(word, 0, 1);
        backward[len]->updateTree(word, len - 1, -1);
    }
    
    int n = words.size();
    vector<int> answer;
    unordered_map<string, int> record;
    for (const string& query : queries) {
        if (record.find(query) != record.end()) {
            answer.push_back(record[query]);
            continue;
        }
        
        int len = query.size(), ans = 0;
        if (query[0] == '?') {
            if (backward.find(len) != backward.end()) 
                backward[len]->countCandidates(query, len - 1, -1, ans);
        } else {
            if (forward.find(len) != forward.end())
            	forward[len]->countCandidates(query, 0, 1, ans);
        }
        answer.push_back(ans);
        record[query] = ans;
    }
    
    return answer;
}