/*
ID: cbnewma1
LANG: C++
TASK: camelot
*/
#include <fstream>
#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <cmath>
using namespace std;

int r, c;

int main() {
	//cerr << "Program started" << endl;
	// read the input
	ifstream fin ("camelot.in");
	ofstream fout ("camelot.out");
	char tc;
	int tr;
	fin >> r >> c;
	//cerr << "Read in row and column nums" << endl;
	//cout << r << " " << c << endl;
	int king[2];
	fin >> tc >> tr;
	king[0] = tr - 1;
	king[1] = tc - 'A';
	//cerr << "Set up king placement" << endl;
	//cout << king[0] << " " << king[1] << endl;
	vector <pair <int, int> > knights;
	while (fin >> tc >> tr) {
		knights.push_back(make_pair(tr - 1, tc - 'A'));
	}
	//cerr << "Added all knights to vector" << endl;
	if (knights.size() == 0) {
		cout << "hi"<< endl;
		fout << '0' << endl;
		return 0;
	}
	
	//cout << knights.size() << endl;
	
	//create board for king distances
	
	int king_board[r][c]; int loc_board[r][c];
	int king_move_r[] = {1,1,1,0,0,-1,-1,-1};
	int king_move_c[] = {1,0,-1,1,-1,1,0,-1};
	int start_r, start_c, cr, cc;
	//cerr << "Instantiated 2 board arrays" << endl;
	for (int i = 0; i < r; i++) { for (int j = 0; j < c; j++) { king_board[i][j] = 10000; loc_board[i][j] = 0;}}
	king_board[king[0]][king[1]] = 0;
	
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			king_board[i][j] = max (abs (king[0]-i),  abs (king[1]-j));
		}
	}
	
	// for (int i = 0; i < r; i++) {
// 		for (int j = 0; j < c; j++) {
// 			cout << king_board[i][j] << " ";
// 		}
// 		cout << endl;
// 	}
// 	
	//cerr << "Finished Calculating king distances" << endl;
	int knight_move_r[] = {-2,-2,-1,-1, 1,1, 2,2};
	int knight_move_c[] = {-1, 1,-2, 2,-2,2,-1,1};
	queue<pair<int, int> > kq;

	int old_r, old_c;
	int board[r][c]; int min_king_board[r][c]; int loc_king_board[r][c];
	for (int i = 0; i < r; i++) { for (int j = 0; j < c; j++) {board[i][j] = 0; min_king_board[i][j] = 10000;}}
	
	//cerr << "Instantiated variables for calculating knight distances" << endl;
	for (int mindex = 0; mindex < knights.size(); mindex++) {
		//cout << "Knight number: " << mindex+1 << ": "<< loc_board[0][0] << endl;
		//Djikstras to find min distance from each knight to each square
		
		//reset the arrays that are used for each knight

		kq.push(make_pair(knights[mindex].first, knights[mindex].second));
		for (int i = 0; i < r; i++) { for (int j = 0; j < c; j++) {loc_board[i][j] = 10000; loc_king_board[i][j] = king_board[i][j];}}
		loc_board[knights[mindex].first][knights[mindex].second] = 0;
		
		while (kq.size() > 0) {
			start_r = kq.front().first;
			start_c = kq.front().second; kq.pop();
			//if (mindex == 4) cout << start_r << ", " << start_c << ", " << loc_king_board[start_r][start_c] << endl;
			for (int m = 0; m < 8; m++) {
				cr = knight_move_r[m]+start_r;
				cc = knight_move_c[m]+start_c;
				
				//if (mindex == 554 && cr == 1 && cc == 2 && loc_board[1][2] == 10000) cout << loc_board[cr][cc] << endl << endl;
				//if (mindex == 554 && loc_board[1][2] == 10000) cout << start_r << ", " << start_c << endl;
				if (cr < 0 || cr >= r || cc < 0 || cc >= c){// || loc_board[cr][cc] <= loc_board[start_r][start_c]+1) {
					//if (mindex == 12 && start_r == 2 && start_c == 1) cout << "HEY!!!!" << endl;
					continue;
				}
			
			
				if (loc_board[cr][cc] >= loc_board[start_r][start_c] + 1 && 
					loc_king_board[start_r][start_c] < loc_king_board[cr][cc]) {
						loc_king_board[cr][cc] = loc_king_board[start_r][start_c];
					
				}
	
				if (loc_board[cr][cc] > loc_board[start_r][start_c] + 1) {
					//if (mindex == 554 && cr == 1 && cc == 2) cout << "Hi" << endl;
					kq.push(make_pair(cr, cc));
					loc_board[cr][cc] = loc_board[start_r][start_c]+1;
					
					
				}	
				
				
			
				
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				board[i][j] += loc_board[i][j];
				if (loc_king_board[i][j] < min_king_board[i][j]) {
					min_king_board[i][j] = loc_king_board[i][j];
				}
				//if (mindex == 4) cout << loc_king_board[i][j] << " ";
			}
			//if (mindex == 4) cout << endl;
		}
		//if (mindex == 4) cout << endl;
	}

	int min = 100000;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
		
			if (board[i][j] + min_king_board[i][j] < min) {
				min = board[i][j] + min_king_board[i][j];
			}
	
			// if (board[i][j] < min) {
// 				min = board[i][j];
// 			}
			
			//if (board[i][j] + min_king_board[i][j] == 4486) {
				//cout << i << ", " << j << endl;
			//}
			//cout << board[i][j] << " ";
		}
		//cout << endl;
	}
	
	fout << min << endl;
	
	return 0;
}
