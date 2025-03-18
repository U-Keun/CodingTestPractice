#include <stdio.h>
#define MAX 1000000

int stack[MAX],
    top = -1;

void push(int num) {
    if (top < MAX - 1) stack[++top] = num;
}

void pop() {
    if (top == -1) printf("-1\n");
    else printf("%d\n", stack[top--]);
}

void get_size() {
    printf("%d\n", top + 1);
}
    
void is_empty() {
    printf("%d\n", top == -1);
}

void peak() {
    if (top == -1) printf("-1\n");
    else printf("%d\n", stack[top]);
}

int main(int argc, char *argv[])
{
    int n, q, num;
    scanf("%d", &n);

    while (n--) {
        scanf("%d", &q);
        switch (q) {
            case 1:
                scanf("%d", &num);
                push(num);
                break;
            case 2:
                pop();
                break;
            case 3:
                get_size();
                break;
            case 4:
                is_empty();
                break;
            case 5:
                peak();
                break;
        }
    }
    return 0;
  
}