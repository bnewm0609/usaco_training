/*
ID: cbnewma1
LANG: C++
TASK: friday
*/
#include <iostream>
#include <string>
#include <fstream>
using namespace std;

int main() {
	ifstream fin ("friday.in");
	ofstream fout ("friday.out");
	int N, currday = 2;
	fin >> N;
	int month_lens[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int days[7]; for (int i = 0; i < 7; i++) days[i] = 0;
	for (int year = 1900; year < 1900 + N; year++) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) month_lens[1] = 29;//is a leap year
		else month_lens[1] = 28;
		for (int mon = 0; mon < 12; mon++) {
			for (int i = 1; i <= month_lens[mon]; i++) {
				if (i == 13) days[currday]++;
				currday = (currday+1) % 7;
			}
		}
	}
	
	for (int i = 0; i < 6; i++) {
		fout << days[i] << " ";
	}
	fout << days[6] << endl;
	return 0;
}