/*
ID: cbnewma1
LANG: C++
TASK: rockers
*/
#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

int n, t, m;
int song_times[20];
int num_songs_used = 0;
int max_songs = 0;
int total_times[20];

int fill_cds(int total_times_index, int song_index, int disks_num, int total_songs) {
	
	if (disks_num > m || song_index >= n) {
		return total_songs;
	} else {
		int f = 0;
		//we do choose this index
		if (total_times[total_times_index] + song_times[song_index] <= t) {
			total_times[total_times_index] += song_times[song_index];
			f = fill_cds(total_times_index, song_index+1, disks_num, total_songs+1);
			total_times[total_times_index] -= song_times[song_index];
		} else {
			//we need to choose a new disk
			if (disks_num < m) {
				total_times[total_times_index+1] = song_times[song_index];
				f = fill_cds(total_times_index+1, song_index+1, disks_num+1, total_songs+1);
			}
		}
		//we don't choose this index
		int f2 = fill_cds(total_times_index, song_index+1, disks_num, total_songs);
		
		return max(f2, f);
	}
}

int main() {
	ifstream fin ("rockers.in");
	ofstream fout ("rockers.out");
	fin >> n >> t >> m;
	for (int i = 0; i < n; i++) {
		fin >> song_times[i];
		songs_used[i] = false;
		//which_disk[i] = 0;
		total_times[i] = 0;
	}
	
	//fill_multiple_cds(0, 0, m-1, 0, 0);
	int f = 0, max_f = 0;
	for (int i = 0; i < n; i++) {
		//cout << endl << "Starting with " << i << endl;
		if (song_times[i] <= t) {
			total_times[0] = song_times[i];
			f = fill_cds(0, i+1, 1, 1);
		}
		if (f > max_f) max_f = f;
		for (int j = 0; j < n; j++) {
			total_times[i] = 0;
		}
		//cout << endl;
	}
	
	fout << max_f << endl;
	return 0;
}
