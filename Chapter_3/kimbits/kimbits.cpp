/*
ID: cbnewma1
LANG: C++
TASK: kimbits
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;




int size_of_set[33][33];
ifstream fin ("kimbits.in");
ofstream fout ("kimbits.out");

void print_num(long long num_bits, long long num_ones, long long index) {
	if (num_bits == 0)
		return;
		
	int sos = size_of_set[num_bits-1][num_ones];
	if (index < sos) {
		fout << "0";
		print_num(num_bits - 1, num_ones, index);
	} else {
		fout << "1";
		print_num(num_bits - 1, num_ones - 1, index - sos);
	}
}


int main() {
	
	long long N, L, I;
	fin >> N >> L >> I;
	//set up the number of combinations 
	for (int i = 0; i < 33; i++) {
		size_of_set[0][i] = 1;
	}
	for (int i = 1; i < 33; i++) {
		for (int j = 0; j < 33; j++) {
			if (j == 0) size_of_set[i][j] = 1;
			else size_of_set[i][j] = size_of_set[i-1][j-1] + size_of_set[i-1][j];
		}
	}
	
	print_num(N, L, I-1);
	fout << endl;
	
	return 0;
}