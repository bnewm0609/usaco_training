/*
ID: cbnewma1
LANG: C++11
TASK: shopping
*/
#include <iostream>
#include <fstream>
#include <vector>
#include <utility>
#include <map>
#include <math.h>
using namespace std;

// map <int, int> left_to_buy;
// int single_prices[1000][6];
// vector <vector <pair <int, int> > > deals;
// vector <int> prices;
// int min_price = 25000;
// void calc_min_price(int curr_price) {
// 	//loop through all of the deals in the deals vector
// 	bool deals_work = false, skip = false;
// 	int test;
// 	for (int i = 0; i < deals.size(); i++) {
// 		skip = false;
// 		for (int j = 0; j < deals[i].size(); j++) {
// 			//if using the given deal results in a negative left_to_by then continue
// 			test = left_to_buy[deals[i][j].first] - deals[i][j].second;
// 			//cout << deals[i][j].first << ": " << test << endl;
// 			if (test < 0) {
// 				skip = true;
// 				for (int k = 0; k < j; k++) {
// 					left_to_buy[deals[i][k].first] += deals[i][k].second;
// 				}
// 				break;
// 		
// 		
// 			//if using the given deal results in any positive left_to_by then subtract and recurse
// 			} else {
// 				//cout << test << endl;
// 				left_to_buy[deals[i][j].first] = test;
// 			}	
// 		}
// 		if (!skip) {
// 			for (auto it = left_to_buy.begin(); it != left_to_buy.end(); it++) {
// 				//cout << it->first << ": " << it->second << "\t";
// 			}
// 			//cout << "hello" << endl;
// 			//cout << endl;
// 			deals_work = true;
// 			calc_min_price(curr_price+prices[i]);
// 			for (int j = 0; j < deals[i].size(); j++) {
// 				left_to_buy[deals[i][j].first] += deals[i][j].second; //add back what we took away to find other possible solutions
// 			}			
// 		}
// 	}
// 
// 	//if none of the deals work, fill in the rest with the single items
// 	if (!deals_work) {
// 		for (auto it = left_to_buy.begin(); it != left_to_buy.end(); it++) {
// 			curr_price += single_prices[it->first][it->second];
// 			//cout << it->second << endl;
// 			//cout << it->first << ": " << it->second << "\t";
// 		}
// 		//cout << endl;
// 		
// 		if (curr_price < min_price) {
// 			//cout << curr_price << endl;
// 			min_price = curr_price;
// 		}
// 	}
// 	//compare current price to min price, if less, min price is current price.
// 	
// }




int tp(int n) {
	int p = 1;
	for (int i = 0; i < n; i++, p*=10);
	return p;
}

int prices[6][6][6][6][6];
int deals[100][5];
int deal_lens[100];
int deal_prices[100];
int goal[5];
int min_price = 25000;
map <int, int> tr;
int main() {
	//cout << tp(4) << endl;
	ifstream fin ("shopping.in");
	ofstream fout("shopping.out");
	
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 5; j++) {
			deals[i][j] = 0;
		}
	}
	//parse the input
	int s, n, c, k, b, p, count = 0;
	fin >> s;
	for (int i = 0; i < s; i++) {
		fin >> n;
		deal_lens[i] = n;
		for (int j = 0; j < n; j++) {
			fin >> c >> k;
			
			auto it = tr.find(c);
			if (it == tr.end())
				tr[c] = count++;
			
			deals[i][tr[c]] = k;
			//cout << c << ": " << k << "\t";
		}
		//cout << endl;
		fin >> p;
		deal_prices[i] = p;
		
	}
	
	fin >> b;
	int ps[5]; for (int i = 0; i < 5; i++) {ps[0]=0;}
	for (int i = 0; i < b; i++) {
		fin >> c >> k >> p;
		
		auto it = tr.find(c);
		if (it == tr.end())
			tr[c] = count++;
		
		goal[tr[c]] = k;
		ps[tr[c]] = p;
		// for (int j = 1; j <= goal[tr[c]]; j++) {
// 			//cout << c << " " << j << " " << p << endl;
// 			prices[tp(tr[c])] = p*j;
// 		}
	}
	prices[1][0][0][0][0] = ps[0];
	prices[0][1][0][0][0] = ps[1];
	prices[0][0][1][0][0] = ps[2];
	prices[0][0][0][1][0] = ps[3];
	prices[0][0][0][0][1] = ps[4];
	
	//cout << prices[1][0][0][0][0] << " " <<prices[0][1][0][0][0] << " " << prices[0][0][1][0][0] << " " << prices[0][0][0][1][0] << " " << prices[0][0][0][0][1] << endl;
	
	for(int a=0;a<=5;a++){for(int b=0;b<=5;b++){for(int c=0;c<=5;c++){for(int d=0;d<=5;d++){for(int e=0;e<=5;e++){
		prices[a][b][c][d][e] = a*prices[1][0][0][0][0]+b*prices[0][1][0][0][0]+c*prices[0][0][1][0][0]+d*prices[0][0][0][1][0]+e*prices[0][0][0][0][1];
		//cout << a << b << c << d << e << endl;
	}}}}}
	//cout << prices[5][5][5][5][5] << endl;
	int ind, ind_p;
	for (int i = 0; i < s; i++) {
		for(int a=0;a<=5-deals[i][0];a++){for(int b=0;b<=5-deals[i][1];b++){for(int c=0;c<=5-deals[i][2];c++){for(int d=0;d<=5-deals[i][3];d++){for(int e=0;e<=5-deals[i][4];e++){
			//ind = tp(0)*(a+deals[i][0])+tp(1)*(b+deals[i][1])+tp(2)*(c+deals[i][2])+tp(3)*(d+deals[i][3])+tp(4)*(e+deals[i][4]);
			//ind_p = tp(0)*a+tp(1)*b+tp(2)*c+tp(3)*d+tp(4)*e;
			prices[a+deals[i][0]][b+deals[i][1]][c+deals[i][2]][d+deals[i][3]][e+deals[i][4]]= min(prices[a+deals[i][0]][b+deals[i][1]][c+deals[i][2]][d+deals[i][3]][e+deals[i][4]], prices[a][b][c][d][e] + deal_prices[i]);
			if (a+b+c+d+e+deals[i][0]+deals[i][1]+deals[i][2]+deals[i][3]+deals[i][4] >= 20) {
				//cout << a+b+c+d+e+deals[i][0]+deals[i][1]+deals[i][2]+deals[i][3]+deals[i][4] << endl;
				//cout << deals[i][0] << " " <<  deals[i][1] << " " << deals[i][2] << " " << deals[i][3] << " " << deals[i][4] << endl;
			}		
		}}}}}//cout << prices[5][5][5][5][5] << endl;}
	}
	
	min_price = prices[goal[0]][goal[1]][goal[2]][goal[3]][goal[4]];
	//calc_min_price(0);
	fout << min_price << endl;
	return 0;
}
























