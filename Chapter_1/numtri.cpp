/*
ID: cbnewma1
LANG: C++
TASK: numtri
*/
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
using namespace std;

int main() {
	ifstream fin("numtri.in");
	ofstream fout("numtri.out");
	int N, t;
	//cout << "Starting" << endl;
	fin >> N;
	//cout << N << endl;
 	vector <vector <int> > triangle;
 	vector <int> row;
 	for (int i  = 1; i <= N; i++){
 		for (int j = 0; j < i; j++) {
			fin >> t;
			row.push_back(t);
		}
		triangle.push_back(row);
		row.clear();
	}
	
	for (int i = N - 2; i >= 0; i--) {
		for (int j = 0; j <= i; j++) {
			triangle[i][j] += max(triangle[i+1][j], triangle[i+1][j+1]);
		}
	}
	fout << triangle[0][0] << endl;
	//cout << triangle[0][0] << endl;
	
	// for (int i = 0; i < N; i++) {
// 		for (int j = 0; j <= i; j++) {
// 			cout << triangle[i][j] << " ";
// 		}
// 		cout << endl;
//  	}
	return 0;
}