/*
ID: cbnewma1
LANG: C++11
TASK: contact
*/
#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

struct comp {
	bool operator() (string one, string two) {
		 if (one.size() < two.size()) return true;
		 else if (one.size() == two.size()) return stoi(one, nullptr, 2) < stoi(two, nullptr, 2);
		 return false;
	};
} mycomp;

int main() {
	ifstream fin("contact.in");
	ofstream fout("contact.out");
	int A, B, N, max_freq = 0, next_freq = 0;
	string message, m, curr;
	//read inputs
	fin >> A >> B >> N;
	map <string, int> freqs;
	vector <string> line;
	while (getline(fin, m)) {
		message += m;
	}
//	fout << message << endl;
	//read chunks of size A to B from message and store frequencies of each chunk in int[]
	for (int i = A; i <= B && i <= message.size(); i++) {
		for (int j = 0; j <= message.size() - i; j++) {
			curr = message.substr(j, i);
			freqs[curr]++;
			if (freqs[curr] > max_freq) max_freq = freqs[curr];
		}
	}
	//read out N highest frequencies
// 	if (message.size() < 6)
// 		N = min(N, (1 << message.size()) - 1);
	while (N > 0) {
		if (max_freq == 0) break;
		fout << max_freq << endl;
		for (const auto &p : freqs) {
			if (p.second == max_freq) {
				//fout << p.first << " ";
				line.push_back(p.first);
				freqs[p.first] = -1;
			}
			else if (p.second > next_freq) {
				next_freq = p.second;
			}
		}
		sort(line.begin(), line.end(), mycomp);
		for (int i = 0; i < line.size(); i++) {
			fout << line[i];
			if (i % 6 == 5 || i == line.size() - 1) fout << endl;
			else fout << " ";
		}
		line.clear();
		max_freq = next_freq;
		next_freq = 0;
		N--;
	}
	
	return 0;
}