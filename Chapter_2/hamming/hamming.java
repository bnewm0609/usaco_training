/*
ID: cbnewma1
LANG: JAVA
TASK: hamming
*/
import java.util.*;
import java.io.*;

public class hamming {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int lineNum = 0;
		//ArrayList<Integer> results = new ArrayList<Integer>();
		int[] results = new int[n];
		int numFound = 1;
		results[0] = 0;
		
		//System.out.println(hammingGreaterThan(7, 0, 3));
		bigloop: for (int i = (int)Math.pow(2, d) - 1; i < Math.pow(2, b+1) && numFound < n; i++) {
			if (!moreBiDiThan(i, d)) {
				continue;
			}
			
			for (int j = numFound; j > 0; j--) {
				if (!hammingGreaterThan(i, results[j], d)) {
					continue bigloop;
				}
			}
			
			results[numFound++] = i;
			
		}
		
		out.print("0");
		for (int k = 1; k < n; k++) {
			if (k%10 == 0) {
				out.println();
			} else {
				out.print(" ");
			}
			out.print(results[k]);
			
		}
		//if (n % 10 != 0)
		out.println();
		out.close();
		System.exit(0);
	}
	
	static boolean moreBiDiThan(int x, int threshold) {
		for (int acc = 0; 0 < x; x/=2) {
			acc += x%2;
			if (acc >= threshold) return true;
		}
		return false;
	}
	
	static boolean hammingGreaterThan(int x1, int x2, int e) {
		return moreBiDiThan(x1^x2, e);
	}
}