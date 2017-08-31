/*
ID: cbnewma1
LANG: C++
TASK: fence9
*/
#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

int main() {
	ifstream fin ("fence9.in");
	ofstream fout ("fence9.out");
	double n, m, p, y;
	fin >> n >> m >> p;
	double slope0 = m/n;
	//cout << slope0<< endl;
	double slope1 = m/(n-p);
	double b = -slope1*p;
	
	int total_lp = 0;
	for (int i = 1; i < n; i++) {
		y = slope0 * i;
		total_lp += ceil(y) - 1;
		//cout << "hi" << endl;
	}
	
	if (p >= n) {
		//cout << "hi" << endl;
		for (int i = max(n, 1.0); i < p; i++) {
			y = slope1*i + b;
			//cout << y << endl;
			total_lp += ceil(y) - 1;
		}
	} else {
		for (int i = n-1; i > p; i--) {
			y = slope1*i + b;
			//cout << y << endl;
			total_lp -= floor(y);
		}
	}
	fout << total_lp << endl;
	return 0;
}