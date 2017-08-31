/*
ID: cbnewma1
LANG: JAVA
TASK: crypt1
*/
import java.util.*;
import java.io.*;
class crypt1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int length = Integer.parseInt(f.readLine());
		int[] set = new int[length];
		boolean[] setB = new boolean[10];
		int nxtVal;
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < length; i++) {
			nxtVal = Integer.parseInt(st.nextToken());
			set[i] = nxtVal;
			setB[nxtVal] = true;
		}
		Arrays.sort(set);
		int sols = solve(set, setB);
		//System.out.println(sols);
		out.println(sols);
		out.close();
		System.exit(0);
	}
	
	static int solve(int[] set, boolean[] setB) {
		HashMap<Integer, Integer> precomp = new HashMap<Integer, Integer>();
		initMap(precomp);
		int numSuccess = 0;
		int minNum = set[0];
		int lim = set.length;
		int threedig;
		int res;
		for (int e1: set) { for (int e2: set) { for (int e3: set) {
			threedig = e1*100 + e2*10 + e3; 
			if (10000.0/threedig < minNum) return numSuccess;
			initMap(precomp);
			for (int i = 0; i < lim; i++) { for (int j = 0; j < lim; j++) {
				if (threedig * set[j] > 1000) lim = j;
				
				//System.out.println(j+": "+precomp.get(j));
				if (precomp.get(set[j]) == -1) {
					res = contains(threedig*set[j], setB);
					precomp.put(set[j], res);
					if (res == 0) continue;
				} else {
					if (precomp.get(set[j]) == 0) continue;
				}
				
				if (precomp.get(set[i]) == -1) {
					res = contains(threedig*set[i], setB);
					precomp.put(set[i], res);
					if (res == 0) continue;
				} else {
					if (precomp.get(set[i]) == 0) continue;
				}
				
				if (contains(threedig* (set[j]+10*set[i]), setB) == 1) {
					numSuccess++;
					//System.out.println(e1+" "+e2+" "+e3+"\n  "+set[i] + " " + set[j]);
				}
			}}
		}}}
		return numSuccess;
	}
	
	static void initMap(HashMap<Integer, Integer> map) {
		for (int i = 0; i < 10; i++) {
			map.put(i, -1);
		}
	}
	
	// static void reset(HashMap<Integer, Integer> map) {
// 		for (int i: map.keySet()) {
// 			map.put(i, -1);
// 		}
// 	}

	static int contains(int num, boolean[] setB) {
		while (num > 0) {
			if (!setB[num%10]) {
				//System.out.println(num);
				return 0;
			}
			num /= 10;
		}
		return 1;
	}
}