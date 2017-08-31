/*
ID: cbnewma1
LANG: C++
TASK: butter
*/
#include <iostream>
#include <fstream>
#include <queue>
#include <vector>

using namespace std;

int MAX_LEN = 226;
int MAX_DIST = MAX_LEN*800;

int main() {
	ifstream fin ("butter.in");
	ofstream fout ("butter.out");
	int N, P, C;
	fin >> N >> P >> C;
	
	int cows[N];
	
	
	int pastures[P][P];
	for (int i = 0; i < P; i++) {
		for (int j = 0; j < P; j++) {
			if (i == j) pastures[i][j] = 0;
			else pastures[i][j] = -1;
		}
	}
	
	int c;
	for (int i = 0; i < N; i++) {
		fin >> c;
		cows[i] = c-1;
		//cout << cows[i] << ", ";
	}
	
	//cout << endl;
	
	int s, e, l;
	for (int i = 0; i < C; i++) {
		fin >> s >> e >> l;
		pastures[s-1][e-1] = pastures[e-1][s-1] = l;
	}
	
	int tot_dist[P];
	int dist[P]; 
	bool visited[P];
	priority_queue<int, vector<int>, greater<int> > q;
	
	for (int i = 0; i < P; i++) {
		tot_dist[i] = 0; 
	}
	int curr_p, min_dist, min_p, num_proc, pos_dist; //min_p is closest pasture
	for (int cw = 0; cw < N; cw++) {
		//Djikstra's algorithm
		//cout << q.size() << endl;
		//reset the distance array and the queue
		for (int i = 0; i < P; i++) {
			dist[i] = MAX_DIST;
			q.push(MAX_DIST*1000+i);
			visited[i] = false;
		}
		
		//start searching at whatever cow we're at
		curr_p = cows[cw];
		dist[curr_p] = 0; //current cow's pasture is the start node
		//num_proc = 0; //so we know when all of the nodes have been considered
		q.push(curr_p);
		
		//cout << "Starting looking at pasture: " << curr_p << endl;
		//q.push(curr_p);
		while (!q.empty()) {
			min_dist = MAX_DIST+1;
			min_p = q.top();
			q.pop();
			while (!q.empty() && visited[min_p%1000]) {
				min_p = q.top();
				q.pop();
			}
			
			if (q.empty()) break;
			// for (int i = 0; i < P; i++) {
// 				//if (pastures[curr_p][i] == -1) continue;
// 			
// 				if (dist[i] < min_dist && !queue[i]) {
// 					min_dist = dist[i];
// 					min_p = i;
// 				}
// 				
// 			}
			//cout << "\tClosest pasture is: " << min_p%1000 << " With a distance of " << dist[min_p%1000] << endl;
			visited[min_p%1000] = true;
			tot_dist[min_p%1000] += dist[min_p%1000];
			//num_proc++;
			
			for (int j = 0; j < P; j++) {
				if (pastures[min_p%1000][j] == -1 || visited[j]) {
					continue;
				}
				//cout << j << endl;
				pos_dist = dist[min_p%1000] + pastures[min_p%1000][j];
				if (pos_dist < dist[j]) {
					dist[j] = pos_dist;
					q.push(pos_dist*1000+j);
				}
			}
			//curr_p = min_p;
	
		}
		// cout << "\tDIST: ";
// 		for (int i = 0; i < P; i++) {
// 			cout << dist[i] << " ";
// 		}
// 		cout << endl;
	}
	
	min_dist = MAX_DIST;
	for (int i = 0; i < P; i++) {
		//cout << tot_dist[i] << " ";
		if (tot_dist[i] < min_dist) {
			min_dist = tot_dist[i];
		}
	}
	//cout << endl;
	fout << min_dist << endl;
	return 0;
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	
	//floyd-warshall
	for (int k = 1; k <= P; k++) {
		for (int i = 1; i <= P; i++) {
			for (int j = 1; j <= P; j++) {
				if (pastures[i][k] + pastures[k][j] < pastures[i][j]) {
					pastures[i][j] = pastures[i][k] + pastures[k][j];
				}
			}
		}
	}
	
	
	int sum2 = 0;
	cout << N << endl;
	//for (int i = 1; i <= P; i++) {
		for (int j = 0; j < N; j++) {
			cout << pastures[31][cows[j]] << " ";
			sum2 += pastures[31][cows[j]];
		}
		cout << sum2 << endl;
		//cout << endl;
		sum2 = 0;
	//}
	
	
	int min_dist = 225*800;
	int sum = 0;
	for (int i = 1; i <= P; i++, sum = 0) {
		for (int j = 0; j < N; j++) {
			sum += pastures[cows[j]][i];
		}
		if (sum < min_dist) {
			min_dist = sum;
			//cout << i << endl;
		}
	}
	
	cout << min_dist << endl;
	
	return 0;
	
}


*/

























