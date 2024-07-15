#include <stdio.h>

int main() {
    char number[1000001];
    
    scanf("%s", number);
    
    int length = 0;
    int converted = 0;
    while (number[length] != '\0') {
        converted += number[length] - '0';
        length++;
        
    }
    
    if (length == 1) {
        if ((number[0] - '0') % 3 == 0) {
            printf("%d\nYES\n", 0);
        } else {
            printf("%d\nNO\n", 0);
        }
        return 0;
    }
    
    int count = 1;
    while (converted >= 10) {
        int value = 0;
        while (converted > 0) {
            value += converted % 10;
            converted /= 10;
        }
        converted = value;
        count++;
    }
    
    printf("%d\n", count);
    if (converted % 3 == 0) {
        printf("YES\n");
    } else printf("NO\n");
    
    return 0;
}