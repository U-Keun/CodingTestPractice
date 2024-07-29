#include <iostream>
#include <vector>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);

using namespace std;

class Node {
    int number;
    Node* left, *right;
public:
    Node(int number);
    void addNode(Node*);
    static void printPostOrder(Node*);
};

Node::Node(const int number) : number(number) {
    this->left = NULL;
    this->right = NULL;
}

void Node::addNode(Node* other) {
    if (this->number > other->number) {
        if (this->left == NULL) {
            this->left = other;
            return;
        }
        (this->left)->addNode(other);
    } else {
        if (this->right == NULL) {
            this->right = other;
            return;
        }
        (this->right)->addNode(other);
    }
}

void Node::printPostOrder(Node* node) {
    if (node->left != NULL) printPostOrder(node->left);
    if (node->right != NULL) printPostOrder(node->right);
    cout << node->number << '\n';
}

int main() {
    FAST_IO();

    vector<int> numbers;
    int input;
    while (1) {
        cin >> input;
        if (cin.eof()) break;
        numbers.emplace_back(input);
    }

    vector<int>::iterator it;
    Node* root = new Node(numbers[0]);
    for (it = numbers.begin() + 1; it != numbers.end(); it++) {
        Node* tmp = new Node(*it);
        root->addNode(tmp);
    }

    Node::printPostOrder(root);

    return 0;
}