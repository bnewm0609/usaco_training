/*
ID: cbnewma1
LANG: C++
TASK: gift1
*/
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

int getIndex(vector<string> v, string s) {
	for (int i = 0; i < v.size(); i++) {
		if (v[i] == s) return i;
	}
	return -1;
}

int main() {
	ifstream fin("gift1.in");
	ofstream fout("gift1.out");
	int np, total, num_gifts, gift_amnt;
	fin >> np;
	vector <int> money;
	vector <string> names;
	string name, recipient;
	for (int i = 0; i < np; i++) {
		fin >> name;
		names.push_back(name);
		money.push_back(0);
	}
	
	for (int i = 0; i < np; i++) {
		fin >> name;
		fin >> total >> num_gifts;
		if (num_gifts == 0) continue;
		gift_amnt = total/num_gifts;
		money[getIndex(names, name)] -= gift_amnt * num_gifts;
		for (int j = 0; j < num_gifts; j++) {
			fin >> recipient;
			money[getIndex(names, recipient)] += gift_amnt;
		}
		
	}
	
	for (int i = 0; i < np; i++) {
		fout << names[i] << " " << money[i] << endl;
	}
	return 0;
}