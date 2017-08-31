/*
ID: cbnewma1
LANG: C++
TASK: hamming
*/
#include <iostream>
#include <fstream>
using namespace std;

int hamming_dist(int i1, int i2) {
	int h = i1 ^ i2, c = 0;
	while (h > 0) {
		if (h % 2 != 0) c++;
		h >>= 1;
	}
	return c;
}

int main() {
	ifstream fin("hamming.in");
	ofstream fout("hamming.out");
	int n, b, d, max, last_word = 0, next_word;
	bool first_word;
	fin >> n >> b >> d;
	max = 1 << b;
	bool code_words[max]; for (int i = 0; i < max; i++) code_words[i] = true;
	fout << 0 << " ";
	for (int i = 2; i <= n; i++) { //keep count of how many code words we have (answering question: do we need to find more words?)
		first_word = true;
		for (int j = last_word + 1; j < max; j++) { //sieve out code words that don't work
			if (hamming_dist(last_word, j) < d) code_words[j] = false;
			else if (first_word && code_words[j]) {
				next_word = j;
				first_word = false;
			}
		}
		fout << next_word;
		if (i % 10 == 0 || i == n) fout << endl;
		else fout << " ";
		last_word = next_word;
	}
	
	return 0;
}