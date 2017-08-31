/*
ID: cbnewma1
LANG: C++
TASK: fence
*/

#include <iostream>
#include <fstream>
using namespace std;

int fences[501][501];
int circuit[1025];
int circuit_counter;


void find_circuit(int curr_node, int max_node) {
	//find all the nodes connected to the current node
	//cout << curr_node << endl;
	for (int i = 1; i <= max_node; i++) {
		//run `find_circuit` on the next node
		if (fences[curr_node][i] > 0) {
			fences[i][curr_node]--;
			fences[curr_node][i]--;
			find_circuit(i, max_node);
		}
	}
	
	//print out current node
	circuit[circuit_counter++] = curr_node;
	//cout << curr_node << endl;
	
}


int main() {
	//read the input and set up the data structures
	ifstream fin ("fence.in");
	ofstream fout("fence.out");
	int F;
	fin >> F;
	//cout << F;
	for (int i = 1; i <= 500; i++){ for (int j = 1; j <= 500; j++) {fences[i][j] = 0;}} //by default none of the nodes are connected
	for (int i = 0; i < 1025; i++) { circuit[i] = -1;}
	circuit_counter = 0;
	int f1, f2, max_node = 0;
	bool node_count_odd[501]; for (int i = 0; i < 501; i++) {node_count_odd[i] = false;}
	for (int i = 0; i < F; i++) {
		fin >> f1 >> f2;
		//cout << f1 << " " << f2 << endl;
		if (f1 > max_node) max_node = f1;
		if (f2 > max_node) max_node = f2;
		node_count_odd[f1] ^= 1;
		node_count_odd[f2] ^= 1;
		fences[f1][f2]++;
		fences[f2][f1]++;
	}
	
	int start_node = 1;
	for (int i = 1; i <= max_node; i++) {
		if (node_count_odd[i]) {
			start_node = i;
			break;
		}
	}
	
	//call the eulerian circuit function which will run recursively
	find_circuit(start_node, max_node);
	for (int i = circuit_counter-1; i >= 0; i--) { //`circuit_counter` will be one more than the number of intersections b/c we always add one when we add a new node
		fout << circuit[i] << endl;
	}
	
	
	return 0;
}