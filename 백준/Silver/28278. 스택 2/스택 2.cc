#include <iostream>
#include <vector>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);
#define MAX 1000000

using namespace std;

class Stack {
    int array[MAX];
    int top;
public:
    Stack() {
        fill(begin(array), end(array), -1);
        this->top = -1;
    }
    void push(int);
    void pop();
    void getSize();
    void isEmpty();
    void peak();
};

void Stack::push(const int num) {
    this->array[++top] = num;
}

void Stack::pop() {
    this->peak();
    if (top > -1) this->array[top--] = -1;
}

void Stack::getSize() {
    cout << this->top + 1 << '\n';
}

void Stack::isEmpty() {
    cout << (this->top < 0) << '\n';
}

void Stack::peak() {
    if (top == -1) cout << -1 << '\n';
    else cout << this->array[top] << '\n';
}

int main() {
// int algorithm() {
    FAST_IO();

    int n;
    cin >> n;

    Stack stack;

    vector<void (Stack::*)() > functions;

    functions.emplace_back(&Stack::pop);
    functions.emplace_back(&Stack::getSize);
    functions.emplace_back(&Stack::isEmpty);
    functions.emplace_back(&Stack::peak);

    int q, num;
    while (n-- > 0) {
        cin >> q;
        if (q == 1) {
            cin >> num;
            stack.push(num);
            continue;
        }
        (stack.*functions[q - 2])();
    }

    return 0;
}