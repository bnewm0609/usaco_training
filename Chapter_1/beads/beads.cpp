/*
ID: cbnewma1
LANG: C++
TASK: beads
*/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int prev(int i, int n){
	if (i <= 0) return n + i - 1;
	else return i - 1;
}


int main() {
	ifstream fin ("beads.in");
	ofstream fout ("beads.out");
	int N, max = 0, before = 0, before1 = 0, after = 0, a,b,aa, bb;
	char bs, as;
	const char *beads;
	string beadstr;
	fin >> N;
	fin >> beadstr;
	beadstr += beadstr;
	beads = beadstr.c_str();
	//cout << beads << endl;
	
	for (int i = 1; i < 2*N; i++) { //we're breaking at each bead
		//calculate number before
		b = i - 1;
		bs = beads[b];
		if (beads[i] == 'w') continue;
		if (beads[b] == 'w') {
			before1 = 0;
			bb = b;
			bs = 'r';
			while ((beads[b] == bs || beads[b] == 'w') && b >= 0 && bb - N < b) {
				before1++;
				b--;
			}
			bs = 'b';
			b = i - 1;
		}
		
		
		bb = b;
		while ((beads[b] == bs || beads[b] == 'w') && b >= 0 && bb - N < b) {
			before++;
			b--;
		}
		//calculate number after
		a = i;
		aa = a;
		as = beads[i];
		while ((beads[a] == as || beads[a] == 'w') && a <= b + N && a < 2*N && aa + N > a) {
			after++;
			a++;
			//cout << "hi" << endl;
		}
		//cout << after << " " << before << endl;
		if (after + before >  max) {
			max = after + before;
		}
		after = 0; before = 0;
	}
	if (max == 0)  max = N; //they're all white
	fout << max << endl;
 	
	return 0;
}