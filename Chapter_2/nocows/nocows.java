/*
ID: cbnewma1
LANG: JAVA
TASK: nocows
*/

import java.util.*;
import java.io.*;

public class nocows {

	static long[][] table;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		if (n%2 == 0 || Math.pow(2, k) < n) {
			out.println(0);
			out.close();
			System.exit(0);
		}
		
		table = new long[n-1][k];
		
		long ans = count(n-1, k-1);
		//System.out.println(ans);
		long ans2 = count(n-1, k-2);
		//System.out.println(ans2);
		if (ans2 < ans){
			out.println((ans-ans2) % 9901);
			//System.out.println((ans-ans2)%9901);
		} else {
			out.println((9901+ans-ans2) % 9901);
			//System.out.println((9901+ans-ans2) % 9901);
		}
		
		out.close();
		System.exit(0);
	}
	
	static long count(int toPutOn, int level) {
		// if (toPutOn <= 3)
// 			return 1;
		
		int start = Math.min((int) Math.pow(2, level) - 1, toPutOn-1);
		long totCount = 0;
		//boolean flag = false;
		for (int i = start; i >= toPutOn - start; i-=2) {
			//System.out.println("in for loop. (" + i + ", " + (toPutOn-i) + ")");
			if (table[toPutOn-i][level] == 0) {
				if (isOneLessThanPowerOfTwo(toPutOn-i, level) || toPutOn-i <= 3) {
					table[toPutOn-i][level] = 1;
					//cnt+=1;
				} else {
					table[toPutOn-i][level] = count(toPutOn-i-1, level - 1);
					//cnt += table[toPutOn-i][level];
				}
			}
			
			
			if (table[i][level] == 0) {
				if (isOneLessThanPowerOfTwo(i, level) || i <= 3) {
					table[i][level] = 1;
					//cnt += 1;
				} else {
					table[i][level] = count(i-1, level - 1);
					//cnt += table[i][level];
				}
			}
			
			totCount += (table[i][level] * table[toPutOn-i][level]) % 9901;
			//System.out.println("totCount: " + totCount + "\ttable["+i+"]["+level+"] = " + table[i][level] + "\ttable["+(toPutOn-i)+"]["+level+"] = " + table[toPutOn-i][level]);
		}
		//cnt+=totCount;
		return totCount % 9901;
	}
	
	static boolean isOneLessThanPowerOfTwo(int n, int exp) {
		return Math.pow(2, exp) - 1 - n < Math.pow(10, -9);
	}
}