/*
ID: cbnewma1
LANG: JAVA
TASK: lamps
*/
import java.util.*;
import java.io.*;

public class lamps {
	static boolean[][] patterns = { //numerical order
		{false}, 
		{false, false, true, true, true, false},
		{false, true},
		{false, true, true},
		{true, false, false},
		{true, false},
		{true, true, false, false, false, true},
		{true} 
	};
	
	static int[] thresh = {1, 2, 1, 1, 2, 1, 2, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		StringTokenizer st;
		
		boolean[] targets = new boolean[8];
		Arrays.fill(targets, true);
		int n = Integer.parseInt(f.readLine());
		int c = Integer.parseInt(f.readLine());
		int[] onLights = new int[n];
		int[] offLights = new int[n];
		
		//read in the inputs
		int l = 0;
		int i = 0;
		st = new StringTokenizer(f.readLine());
		while (true) {
			l = Integer.parseInt(st.nextToken());
			if (l == -1)
				break;
			onLights[i] = l;
			i++;
		}
		
		l = 0;
		i = 0;
		st = new StringTokenizer(f.readLine());
		while (true) {
			l = Integer.parseInt(st.nextToken());
			if (l == -1)
				break;
			offLights[i] = l;
			i++;
		}
		
		// System.out.println("off lights");
// 		for (int j = 0; j < n; j++) {System.out.print(offLights[j] + " ");}
// 		System.out.println("\non lights");
// 		for (int j = 0; j < n; j++) {System.out.print(onLights[j] + " ");}
// 		System.out.println();
// 		
		// for (int j = 0; j < n && offLights[j] != 0; j++) {
// 			for (int k = 0; k < 8; k++) {
// 				if (c == thresh[k] || c >= thresh[k] + 2) {
// 					System.out.println(k);
// 					if (patterns[k][(offLights[j]-1) % patterns[k].length]) {
// 						targets[offLights[j]-1] = false;
// 					}
// 				} else {
// 					targets[offLights[j]-1] = false;
// 				}
// 			}
// 		}
		
		for (int k = 0; k < 8; k++) {
			if (c == thresh[k] || c >= thresh[k] + 2) {
				//System.out.println(k);
				for (int j = 0; j < n && onLights[j] != 0; j++) {
					
					if (!patterns[k][(onLights[j]-1) % patterns[k].length]) {
						targets[k] = false;
					}
				}
				
				for (int j = 0; j < n && offLights[j] != 0; j++) {
					if (patterns[k][(offLights[j]-1) % patterns[k].length]) {
						targets[k] = false;
					}
				} 
			} else {
				targets[k] = false;
			}
		}
		
		// System.out.println("targets");
// 		for (int j = 0; j < 8; j++) {System.out.print(targets[j] + " ");}
// 		System.out.println();
		
		boolean flag = false;
		for (int j = 0; j < 8; j++) {
			if (targets[j]) {
				flag = true;
				for (int k = 0; k < n; k++) {
					out.print(patterns[j][k % patterns[j].length] ? "1" : "0");
				}
				out.println();
			}
		}
		
		if (!flag)
			out.println("IMPOSSIBLE");
		
		out.close();
		System.exit(0);
	}
}