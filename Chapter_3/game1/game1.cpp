/*
ID: cbnewma1
LANG: C++
TASK: game1
*/
#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

int main() {
	ifstream fin ("game1.in");
	ofstream fout ("game1.out");
	int n;
	fin >> n;
	int board[n]; int best_sum_btwn[n][n]; int tot_sum_btwn[n][n];
	
	// for (int i = 0; i < n; i++) {
// 		for (int j = 0; j < n; j++) {
// 			best_sum_btwn[i][j] = 0;
// 			tot_sum_btwn[i][j] = 0;
// 		}
// 	}
	
	for (int i = 0; i < n; i++) {
		fin >> board[i];
		best_sum_btwn[i][i] = board[i];
		tot_sum_btwn[i][i]  = board[i];
	}
	
	//calculate the arrays of total sums
	for (int d = 1; d < n; d++) { //distance b/w index
		for (int i = 0; d + i < n; i++) {
			tot_sum_btwn[i][i+d] = tot_sum_btwn[i][i]+tot_sum_btwn[i+1][i+d];
			tot_sum_btwn[i+d][i] = tot_sum_btwn[i][i+d];
		}
	}
	
	// for (int i = 0; i < n; i++) {
// 		for (int j = 0; j < n; j++) {
// 			cout << tot_sum_btwn[i][j] << " ";
// 		}
// 		cout << endl;
// 	}
	
	//calculate the best sum between two indices
	for (int d = 1; d < n; d++) {
		for (int i = 0; d + i < n; i++) {
			best_sum_btwn[i][i+d] = max(board[i] + tot_sum_btwn[i+1][i+d] - best_sum_btwn[i+1][i+d],
										board[i+d] + tot_sum_btwn[i][i+d-1]-best_sum_btwn[i][i+d-1]);
		}
	}
	fout << best_sum_btwn[0][n-1] << " " << tot_sum_btwn[0][n-1] - best_sum_btwn[0][n-1] << endl;
	
	
	
	return 0;
}
	// int p_scores[2]; p_scores[0] = 0; p_scores[1] = 0;
// 	int max_scores[2]; max_scores[0] = 0; max_scores[1] = 0;
// 	int i, j, turn_counter;
// 	int test_i, test_j, di, dj;
// 	int sb, se;
// 	
// 	for (int end = 0; end < n; end++) {
// 		turn_counter = n;
// 		//cout << board[end] << endl;
// 		p_scores[turn_counter%2] = board[end];
// 		turn_counter--;
// 		//cout << p_scores[1] << endl;
// 		i = end - 1; j = end + 1;
// 		while (turn_counter > 0) {//(i >= 0 || j < n) {
// 			//cout << p_scores[0] << " " << p_scores[1] << endl;
// 			//cout << i << ", " << j;
// 			test_i = i - 1;
// 			test_j = j + 1;
// 			
// 			if (i < 0) {
// 				p_scores[turn_counter%2] += board[j++];
// 				//cout << " took j" << endl;
// 				//cout << "h1" << endl;
// 			} else if (j >= n) {
// 				p_scores[turn_counter%2] += board[i--];
// 				//cout << " took i" << endl;
// 				//cout << "h2" << endl;
// 			} else {
// 				//cout << "h3" << endl;
// 				//cout << i << ", " << j << endl;
// 				
// 				if (test_i < 0) test_i = i;
// 				if (test_j >= n) test_j = j;
// 				
// 				di = board[i] - board[test_i];
// 				dj = board[j] - board[test_j];
// 				if (di < dj) {
// 					p_scores[turn_counter%2] += board[j++];
// 					//cout << " took j" << endl;
// 					//cout << p_scores[turn_counter%2] << endl;
// 				} else {
// 					p_scores[turn_counter%2] += board[i--];
// 					//cout << " took i" << endl;
// 					//cout << p_scores[turn_counter%2] << endl;
// 				}
// 				
// 				
// 				
// 			}
// 			turn_counter--;
// 			
// 		}
// 		//cout << p_scores[0] << ", " << p_scores[1] << endl;
// 		if  (p_scores[0] > max_scores[0]) {
// 			
// 			max_scores[0] = p_scores[0];
// 			max_scores[1] = p_scores[1];
// 		}
// 		p_scores[0] = 0; p_scores[1] = 0;
// 		
// 	}
// 	
// 	
// 	
// 	
// 	
// 	//while (i <= j) {
// 		
// 		//sb = board[i] - board[i+1];
// 		//se = board[j] - board[j-1];
// 		// if (sb > se) {
// // 			p_scores[turn_counter%2] += board[i++];
// // 		} else if (sb < se) {
// // 			p_scores[turn_counter%2] += board[j--];
// // 		} else {
// // 			if (board[i+1] < board[j-1]) {
// // 				p_scores[turn_counter%2] += board[i++];
// // 			} else {
// // 				p_scores[turn_counter%2] += board[j--];
// // 			}
// // 		}
// 		// if (board[i] > board[j]) {
// // 			p_scores[turn_counter%2] += board[i++];
// // 		} else {
// // 			p_scores[turn_counter%2] += board[j--];
// // 		}
// //		turn_counter++;
// 		//cout << turn_counter << endl;
// //	}
// 	if (max_scores[0] > max_scores[1]) {
// 		fout << max_scores[0] << " " << max_scores[1] << endl;
// 	} else {
// 		fout << max_scores[1] << " " << max_scores[0] << endl;
// 	}
// 	// for (int i = 0; i < n; i++){
// // 		cout << board[i] << " ";
// // 	}
// // 	cout << endl;
//	return 0;
//}