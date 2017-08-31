import java.util.*;
import java.io.*;
class bruteForceCrypt1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int length = Integer.parseInt(f.readLine());
		int[] set = new int[length];
		int nxtVal;
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < length; i++) {
			nxtVal = Integer.parseInt(st.nextToken());
			set[i] = nxtVal;
		}
		Arrays.sort(set);
		int sols = solve(set);
		System.out.println(sols);
		out.println(sols);
		out.close();
		System.exit(0);
	}
	
	static int solve(int[] set) {
		int numSuccess = 0;
		int threedig;
		int twodig;
		for (int e1: set) { for (int e2: set) { for (int e3: set) { 
			threedig = e1*100 + e2*10 + e3; 
			if (10000.0/threedig < set[0]) return numSuccess;
			for (int e4: set) { for (int e5: set) {
				//twodig = e4*10 + e5;
				//System.out.println(twodig);
				//if (e5 == 6) System.out.println("\nHERE: 1");
				if (threedig * (e4*10 + e5) > 9999) continue;
				//if (e5 == 6) System.out.println("\nHERE: 2");
				if (!contains(set, threedig*e4)) continue;
				//if (e5 == 6) System.out.println("\nHERE: 3");
				if (!contains(set, threedig*e5)) continue;
				//if (e5 == 6) System.out.println("\nHERE: 4");
				if (!contains(set, threedig * (e4*10 + e5))) continue;
				//System.out.println(e1+" "+e2+" "+e3+"\n  "+e4 + " " + e5);
				numSuccess++;
			}}
		}}}
		return numSuccess;
	}
	
	static boolean contains (int[] set, int num) {
		if (num < 10) {
			for (int i: set) {
				if (num == i) return true;
			}
			return false;
		} else {
			for (int i: set) {
				if (num%10 == i) return contains(set, num/10);
			}
			return false;
		}
	}
}