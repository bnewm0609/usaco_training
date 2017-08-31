/*
ID: cbnewma1
LANG: C++
TASK: heritage
*/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;


ofstream fout ("heritage.out");

bool processed[27];
string in_order;
string pre_order;
string post_order = "";
int N;
int ind = 0;

void print_post_order() {
	//get next node from pre-order string
	if (ind >= N) return;
	char next_node = pre_order[ind++];
	//cout << ind << ": " << next_node << endl;
	//find next node in the in-order string
	int io_index;
	for (io_index = 0; in_order[io_index] != next_node; io_index++) {}
	
	// check if is leaf
	if (io_index > 0 && processed[io_index-1] && processed[io_index+1]) {
		//fout << next_node;
		post_order += next_node;
		return;
	} else if (io_index == 0) {
		if (processed[io_index+1]) {
			//fout << next_node;
			post_order += next_node;
			return;
		}
	}
	processed[io_index] = true;
	//otherwise print the left and right sub-trees (the ind variable should take care of which one is right and which one is left... hopefully)
	if (io_index > 0 && !processed[io_index-1]) {
		print_post_order();
	}
	if (!processed[io_index+1]) {
		print_post_order();
	}
	//fout << next_node;
	post_order += next_node;
	
}

int main() {
	ifstream fin ("heritage.in");
	fin >> in_order;
	fin >> pre_order;
	N = in_order.length();
	//cout << N << endl;
	for (int i = 0; i < 27; i++) {
		if (i < N) processed[i] = false;
		else processed[i] = true;
	}
	print_post_order();
	fout << post_order << endl;
	//cout << post_order.length() << " characters: " <<  post_order << endl;
	// for (int i = 0; i < N; i++) {
// 		cout << (int) post_order[i] << endl;
// 	}
	return 0;
}