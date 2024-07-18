#define _CRT_SECURE_NO_WARNINGS
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct node {
	int number;
	struct node* prev;
	struct node* next;
} Node;

int main() {
	int n, k;
	scanf("%d %d", &n, &k);

	Node* head;
	head = (Node*) malloc(sizeof(Node));
	head->number = -1;

	Node* current = head;
	for (int i = 0; i < n - 1; i++) {
		Node* tmp = (Node*) malloc(sizeof(Node));
		tmp->number = -1;
		tmp->prev = current;
		current->next = tmp;
		current = tmp;
	}
	current->next = head;
	head->prev = current;

	int r = 0;
	char ch = -1;
	current = head;
	int alphabet[26];
	for (int i = 0; i < 26; i++) alphabet[i] = 0;
	bool isValid = true;
	for (int i = 0; i < k; i++) {
		scanf("%d %c", &r, &ch);

		while (r-- > 0) {
			current = current->next;
		}

		if (current->number == -1) {
			alphabet[ch - 'A']++;
			if (alphabet[ch - 'A'] > 1) {
				isValid = false;
			}
			current->number = ch;
		} else if (current->number != ch) {
			isValid = false;
		}
	}

	if (!isValid) {
		printf("!\n");
		return 0;
	}


	for (int i = 0; i < n; i++) {
		printf("%c", (current->number == -1)? '?' : (char) current->number);
		current = current->prev;
	}
	printf("\n");

	return 0;
}