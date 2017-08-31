/*
ID: cbnewma1
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;

public class humble {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int[] c = new int[k];
		int[] s = new int[k];
		long[] hs = new long[n+1];
		//Arrays.fill(hs, Integer.MAX_VALUE);
		//HashSet<Long> numbers_one = new HashSet<>();
		//numbers_one[0] = false;
		//boolean[] numbers_two = new boolean[n];
		
		long x = 0;
		for (int i = 0; i < k; i++) {
			x = Long.parseLong(st.nextToken());
 			s[i] = (int)x;
//			hs[i] = x;
			// numbers_one.add(l);
// 			numbers_one.add(l*l);
		}
		
		Arrays.sort(s);
		//Arrays.sort(hs);
		int si = 0;
		int minI = -1;
		int i = 1;
		
		hs[0] = s[si++];
		for (int j = 0; j < n; j++) {
			minI = 0;
			
			for (int l = 0; l < k && hs[c[l]] > 0; l++) {
				if (hs[c[minI]] * s[minI] > hs[c[l]] * s[l]) {
					minI = l;
				}
			}
			//System.out.println(min);
			if (si < k && s[si] < hs[c[minI]] * s[minI]) {
				//System.out.println("here");
				hs[i++] = s[si];
				si++;
				
			} else {
				hs[i++] = hs[c[minI]] * s[minI];
				for (int l = 0; l < k; l++) {
					if (hs[i-1] >= hs[c[l]] * s[l]) {
						c[l]++;
						// if (i >= n - 1 || c[l] >= n-1)
	// 						break;
					
						// while (!numbers_one.contains(hs[i]/s[i])) {
	// 						hs[i] += s[i];
	// 					}
	// 					numbers_one.add(hs[i]);
					}
				}
			}
			
			// for (int l = 0; l < n && hs[l] < Integer.MAX_VALUE; l++) {
// 				System.out.print(hs[l] + " ");
// 			}
// 			System.out.println();
		}
		
		
		//System.out.println("leaving the loop");
		// long j = 1;
// 		int h = 0;
// 		while (h < n) {
// 			j++;
// 			if (numbers_one.contains(j)) {
// 				h++;
// 			}
// 			for (int x = 0; x < k; x++) {
// 				if (j%s[x] == 0 && numbers_one.contains(j/s[x])) {
// 					h++;
// 					numbers_one.add(j);
// 					break;
// 				}
// 			}
// 		}
// 		
		// long max = -1;
// 		for (int l = 0; l < n && hs[l] < Integer.MAX_VALUE; l++) {
// 			//System.out.println(hs[i]);
// 			if (hs[l] > max) {
// 				max = hs[l];
// 			}
// 		}
		
		//System.out.println("found max: " + max);
		// int sub = 0;
// 		if (hs[minI] < s[k-1]) {
// 			for (int i = 0; i < k; i++) {
// 				if (hs[minI] < s[i]) {
// 					sub--;
// 				}
// 				if (hs[minI] < s[0]*s[0]) {
// 					sub--;
// 				}
// 			}
// 		}
		//System.out.println(hs[n-1]);
		out.println(hs[n-1]);
		//out.println(h);
		out.close();
		
		
	}
}