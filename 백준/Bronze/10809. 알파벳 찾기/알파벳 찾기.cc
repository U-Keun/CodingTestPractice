#include <stdio.h>
#include <string.h>

int main() {
    char input[101];
    scanf("%s", input);
    
//    printf("%d\n", (int) strlen(input));
    
    short idx[26];
    for (int i = 0; i < 26; i++) {
        idx[i] = -1;
    }
    
    int l = (int) strlen(input);
    
    for (int i = 0; i < l; i++) {
        if (idx[input[i] - 'a'] == -1) {
            idx[input[i] - 'a'] = i;
        }
    }
    
    for (int i = 0; i < 26; i++) {
        printf("%d ", idx[i]);
    }
    printf("\n");
    
    return 0;
}