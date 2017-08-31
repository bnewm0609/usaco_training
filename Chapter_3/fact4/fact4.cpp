/*
ID: cbnewma1
LANG: C++
TASK: fact4
*/

#include <iostream>
#include <fstream>
using namespace std;

int main() {
	ifstream fin ("fact4.in");
	ofstream fout ("fact4.out");
	long N, prod = 1; 
	fin >> N;
	for (int i = 2; i <= N; i++) {
		prod *= i;
		while (prod % 10 == 0) {
			prod /= 10;
		}
		prod %= 10000;
		//cout << i << ": " << prod << endl;;
	}
	fout << prod %10 << endl;
	return 0;
}