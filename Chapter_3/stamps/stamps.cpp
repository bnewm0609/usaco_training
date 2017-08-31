/*
ID: cbnewa1
LANG: C++
TASK: stamps
*/
#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>
#include <cmath>
using namespace std;

vector <int> stamp_values;
vector<int> make_values;
int N, K, temp, min_stamp;

void generate_sums(int curr_sum, int stamps_used, int curr_index) {
	if (stamps_used == K) return;
	for (int i = curr_index; i < N; i++) {
		make_values[stamp_values[i] + curr_sum] = true;
		generate_sums(stamp_values[i] + curr_sum, stamps_used + 1, i);
	}
}

int main() {
	ifstream fin ("stamps.in");
	ofstream fout("stamps.out");
	bool changed = false;
	fin >> K >> N;
	// if (K == 200 && N == 14) {
// 		fout << 200;
// 		return 0;
// 	}
	make_values.assign(K*10000, -1);
	for (int i = 0; i < N; i++) {
		fin >> temp;
		if (temp < min_stamp) min_stamp = temp;
		stamp_values.push_back(temp);
		make_values[temp] = 1;
	}
	
	for (int i = min_stamp + 1; i < K*10000; i++) {
		if (make_values[i] > 0) {
			continue;
		}
		for (int j = 0; j < N; j++) {
			if (i < stamp_values[j]) continue;
			if (make_values[i-stamp_values[j]] < K && make_values[i-stamp_values[j]] > 0) {
				if (make_values[i] == -1) make_values[i] = make_values[i-stamp_values[j]] + 1;
				else make_values[i] = min(make_values[i-stamp_values[j]] + 1, make_values[i]);
			}
		}
	}
	
	//generate_sums(0,0,0);
	int longest_run = 0, curr_run = 0;
	for (int i = 0; i < K*10000; i++) {
		//cout << make_values[i] << " ";
		if (make_values[i] >  0) curr_run++;
		else {
			longest_run = max(longest_run, curr_run);
			curr_run = 0;
		}
	}
	//cout << longest_run << endl;
	fout << longest_run << endl;
	
	
	
	// for (int i = 0; i < N; i++) {
// 		cout << stamp_values[i] << " ";
// 	}
// 	cout << endl;
	return 0;
}
