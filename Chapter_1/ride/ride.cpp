/*
ID: cbnewma1
LANG: C++
TASK: ride
*/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
	ofstream fout("ride.out");
	ifstream fin("ride.in");
	string comet, group;
	fin >> comet >> group;
	
	
	// string comet, group;
// 	getline(fin, comet);
// 	getline(fin, group);
	
	int cometProd = 1;
	int groupProd = 1;
	for (int i = 0; i < comet.length(); i++) {
		cometProd *= (comet.c_str()[i] - 'A' + 1);
	}
	
	for (int i = 0; i < group.length(); i++) {
		groupProd *= (group.c_str()[i] - 'A' + 1);
	}
	
	if (cometProd % 47 == groupProd % 47) fout << "GO\n";
	else fout << "STAY\n";
	
	return 0;
}
