/*
ID: cbnewma1
LANG: JAVA
TASK: skidesign
*/
import java.util.*;
import java.io.*;

public class skidesign {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		int numHills = Integer.parseInt(f.readLine());
		int[] heights = new int[numHills];
		int num, max, min;
		max = 0; min = 101;
		for (int i = 0; i < numHills; i++) {
			num = Integer.parseInt(f.readLine());
			heights[i] = num;
			if (num < min) min = num;
			if (num > max) max = num;
		}
		int minCost = getMinCost(heights, min, max);
		out.println(minCost);
		out.close();
		System.exit(0);
	}
	
	public static int getMinCost(int[] heights, int min, int max) {
		//brute force through all possible ranges of 17 from min - min + 17 to max -17  - max
		int total = 83*83*1000;
		int t = 0;
		for (int i = 0; i < max - min - 17; i++) {
			for (int j = 0; j < heights.length; j++) {
				if (heights[j] < min + i) {
					t += Math.pow(min + i - heights[j], 2);
				} else if (heights[j] > min + 17 + i) {
					t += Math.pow(heights[j] - min - 17 - i, 2);
				}
			}
			if (t < total) total = t;
			t = 0;
		}
		
		if (total == 83*83*1000) {
			return 0;
		} else {
			return total;
		}
	}
}