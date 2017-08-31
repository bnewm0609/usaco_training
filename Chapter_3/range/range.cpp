/*
ID: cbnewma1
LANG: C++
TASK: range
*/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
	ifstream fin ("range.in");
	ofstream fout ("range.out");
	int N;
	fin >> N;
	string rows[N];
	for (int i = 0; i < N; i++) {
		fin >> rows[i];
	}
	int found_squares[N+1]; for (int i = 0; i <=N; i++) {found_squares[i] = 0;}
	
	// add one more row and column to avoid out of bounds issues. This extra row and column just have 0's
	int run_rect_sum[N+1][N+1];
	for (int i = 0; i <= N; i++) {
		run_rect_sum[0][i] = 0;
		run_rect_sum[i][0] = 0;
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			//cout << rows[i-1][j-1] - '0' << " ";
			run_rect_sum[i][j] = run_rect_sum[i-1][j] + run_rect_sum[i][j-1] + (rows[i-1][j-1]-'0') - run_rect_sum[i-1][j-1];
		}
		//cout << endl;
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			for (int side_len = 1; N - i >= side_len && N - j >= side_len; side_len++) {
				if (run_rect_sum[i+side_len][j+side_len] - run_rect_sum[i+side_len][j-1] - run_rect_sum[i-1][j+side_len] + run_rect_sum[i-1][j-1] 
					== (side_len+1)*(side_len+1)) {
					found_squares[side_len+1]++;
				}
			}
		}
	}


	for (int i = 2; i <= N; i++) {
		if (found_squares[i] > 0) {
			fout << i << " " << found_squares[i] << endl;
		}
	}

	return 0;
}