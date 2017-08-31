/*
ID: cbnewma1
LANG: C++
TASK: spin
*/
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;


long gcd(long a, long b) {
	long temp;
	while (b!=0) {
		temp = b;
		b = a % b;
		a = temp;
	}
	return a;
}

long lcm(long a, long b) {
	return a / gcd(a, b) * b;
}


int main() {
	//read in the input
	ifstream fin ("spin.in");
	ofstream fout ("spin.out");
	int speeds[5];
	int num_wedges[5];
	int wedges_start[5][5];
	int wedges_end[5][5]; for (int i = 0; i < 5; i++) {for (int j =0; j < 5; j++) {wedges_start[i][j] = -1; wedges_end[i][j] = -1;}}
	int speed, nw, ws, we; 
	for (int i = 0; i < 5; i++) {
		fin >> speed >> nw;
		speeds[i] = speed;
		num_wedges[i] = nw;
		//cout << nw << endl;
		for (int j = 0; j < nw; j++) {
			fin >> ws >> we;
			wedges_start[i][j] = ws;
			wedges_end[i][j] = (ws+we)%360;
			//cout << ws << "\t" << we << endl;
		}
	}
	
	long min_time = 1;
	for (int i = 0; i < 5; i++) {
		min_time = lcm(min_time, lcm(speeds[i], 360)/speeds[i]);
	}
	//cout << min_time << endl;
	
	
	//run the simulation
	//bool intersection = false;
	int time = 0, wend;
	int wheels[360]; 
	for (time = 0; time <= min_time; time++) {
		
		// for (int i = 0; i < 5; i++) {
// 			cout << (i+1) <<":\t";
// 			for (int j = 0; j < num_wedges[i]; j++) {
// 				cout << wedges_start[i][j] << ": " << wedges_end[i][j] << "\t";
// 			}
// 			cout << endl;
// 		}
// 		cout << endl;
		
		//reset the array holding which sections are covered and update the time
		for (int i = 0; i < 360; i++) wheels[i] = 0;
		//cout << time << endl;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < num_wedges[i]; j++) {
				
				wend = wedges_end[i][j];
				if (wend < wedges_start[i][j]) {
					wend += 360;
				}
				for (int k = wedges_start[i][j]; k <= wend; k++) {
					if (wheels[k%360] == 4) {
						if (time == min_time) cout << "0" << endl;
						else fout << time << endl;
						return 0;
					}
					wheels[k%360]++;
				}
				
				wedges_start[i][j] = (speeds[i] + wedges_start[i][j]) % 360;
				wedges_end[i][j] = (speeds[i] + wedges_end[i][j]) % 360;
				
			}
		}
	}
		
// 		check for collisions
// 		for (int i = 0; i < 4; i++) { for (int j = 0; j < num_wedges[i]; j++) {
// 			for (int k = i+1; k < 5; k++) { for (int l = 0; l < num_wedges[k]; l++) {
// 				if ((wedges_start[i][j] > wedges_end[k][l] && ( wedges_end[k][l] > wedges_start[k][l] || wedges_start[k][l] > wedges_end[i][j])) ||
// 					(wedges_start[k][l] > wedges_end[i][j] && ( wedges_end[k][l] > wedges_start[k][l] || wedges_end[k][l] < wedges_start[i][j]))) {
// 					continue;
// 				} else {
// 					cout << time << endl;
// 					return 0;
// 				}
// 			}}
// 		}}
// 	}
// 	
	
	//output the result
	fout << "none" << endl;
	return 0;
}