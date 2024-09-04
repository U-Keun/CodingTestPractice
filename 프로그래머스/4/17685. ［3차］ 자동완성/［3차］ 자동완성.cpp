#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

class Node {
public:
    unordered_map<char, Node*> children;
    bool isRegistered;
    int counter;
    
    Node() { 
        isRegistered = false; 
        counter = 0;
    }
    void insertChar(char c) {
        if (children.find(c) == children.end()) {
            children[c] = new Node();
        }
    }
    void setRegistered() { this->isRegistered = true; }
    void countUp() { (this->counter)++; }
};

int solution(vector<string> words) {
    Node *root = new Node;
    for (string word : words) {
        Node *current = root;
        for (char c : word) {
            current->insertChar(c);
            current = current->children[c];
            current->countUp();
        }
        current->setRegistered();
    }
    
    int answer = 0;
    for (string word : words) {
        Node *current = root;
        int val = 0;
        for (char c : word) {
            current = current->children[c];
            val++;
            if (current->counter == 1) {
                answer += val;
                break;
            }
            if (val == word.size()) answer += val;
        }
    }
    
    return answer;
}