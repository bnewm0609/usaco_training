/*
ID: cbnewma1
LANG: C++
TASK: ratios
*/
#include <iostream>
#include <fstream>
using namespace std;

int main() {
	ifstream fin ("ratios.in");
	ofstream fout ("ratios.out");
	int mixtures[3][3];
	int output[3];
	short flag;
	
	for (int i = 0; i < 3; i++) {
		fin >> output[i];
		if (output[i] == 0) flag += 1 << i;
		//cout << output[i] << " ";
	}
	//cout << endl << endl;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			fin >> mixtures[i][j];
			//cout << mixtures[i][j] << " ";
		}
		//cout << endl;
	}
	
	int res[3];
	int divres;
	bool r;
	
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			for (int k = 0; k < 100; k++) {
				res[0] = mixtures[0][0]*i + mixtures[1][0]*j + mixtures[2][0]*k;
				res[1] = mixtures[0][1]*i + mixtures[1][1]*j + mixtures[2][1]*k;
				res[2] = mixtures[0][2]*i + mixtures[1][2]*j + mixtures[2][2]*k;
				
				divres = -1;
				
				r = false;
				
				for (int i = 0; i < 3; i++) {
					if (flag & (1 << i)) {
						r = res[i] == 0;
					} else {
						if (divres == -1) divres = res[i]/output[i];
						r = (res[i]%output[i] == 0) && (res[i]/output[i] == divres) && (res[i] >=output[i]);
						if (!r) break;
					}
					
					
				
				// } else if (res[0]%output[0] == 0 && res[1]%output[1] == 0 && res[2]%output[2] == 0) {
// 					if (res[0]/output[0] == res[1]/output[1] && res[2]/output[2] == res[1]/output[1]) {
// 						fout << i << " " << j << " " << k << " " << res[0]/output[0] << endl;
// 						return 0;
// 					}
 				}
 				if (r) {
 					fout << i << " " << j << " " << k << " " << divres << endl;
 					return 0;
 				}
			}
		}
	}
	fout << "NONE" << endl;
	return 0;
}