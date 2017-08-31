/*
ID: cbnewma
LANG: C++
TASK: msquare
*/

#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <algorithm>
#include <queue>
using namespace std;

map<string, string> combos_seen;
queue <string> proc_queue;

string a(string s) {
	string c;
	c.assign(s);
	reverse(c.begin(), c.end());
	return c;
}

string b(string s) {
	return s.substr(3,1) + s.substr(0,3) + s.substr(5,3) + s.substr(4, 1);
}

string c(string s) {
	return s.substr(0,1) + s.substr(6,1) + s.substr(1,1) +s.substr(3,2)+s.substr(2,1)+s.substr(5,1)+s.substr(7,1);
}


int main() {
	ifstream fin ("msquare.in");
	ofstream fout ("msquare.out");
	string goal = "", g, start = "12345678";
	combos_seen[start] =  "";
	proc_queue.push(start);
	for (int i = 0; i < 8; i++) {
		fin >> g;
		goal+=g;
	}
	//cout << start << endl << "a) " << a(start) << endl << "b) " << b(start) << endl << "c) " << c(start) << endl;
	//shortest_path(start, goal, "");
	string curr_str, next_str, curr_path;
	
	while (true) {
		curr_str = proc_queue.front();
		proc_queue.pop();
		curr_path = combos_seen[curr_str];
		
		if (curr_str == goal) {
			break;
		}
		
		next_str = a(curr_str);
		if (combos_seen.find(next_str) == combos_seen.end()){
			combos_seen[next_str] = curr_path + "A";
			proc_queue.push(next_str);
		}
		
		next_str = b(curr_str);
		if (combos_seen.find(next_str) == combos_seen.end()){
			combos_seen[next_str] = curr_path + "B";
			proc_queue.push(next_str);
		}
		
		next_str = c(curr_str);
		if (combos_seen.find(next_str) == combos_seen.end()){
			combos_seen[next_str] = curr_path + "C";
			proc_queue.push(next_str);
		}
		
	}
	
	
	int path_len = curr_path.length();
	fout << path_len << endl;
	for (int i = 0; i <= path_len; i+=60) {
		fout << curr_path.substr(i, 60) << endl;
	}
	if (path_len % 60 != 0 && path_len > 60) fout << curr_path.substr(path_len - (path_len%60), path_len%60) << endl;
	return 0;
}