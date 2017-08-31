/*
ID: cbnewma1
LANG: JAVA
TASK: sort3
*/
import java.util.*;
import java.io.*;

public class sort3 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int[] totals = new int[3];
		int total = Integer.parseInt(f.readLine());
		int[] origList = new int[total];
		
		int temp = 0;
		for (int i = 0; i < total; i++) {
			temp = Integer.parseInt(f.readLine()) - 1; // - 1 goes from 1, 2, 3 --> 0, 1, 2 for array indices
			totals[temp]++;
			origList[i] = temp;
		}
		
		int[][] table = new int[3][3];
		for (int i = 0; i < totals[0]; i++) {
			table[0][origList[i]]++;
		}
		for (int i = totals[0]; i < totals[0] + totals[1]; i++) {
			table[1][origList[i]]++;
		}
		for (int i = totals[0]+totals[1]; i < total; i++) {
			table[2][origList[i]]++;
		}
		
		//print out totals and table
		//for (int i = 0; i < 3; i++) {System.out.print(totals[i]+" ");}
		//System.out.println("\n");
		//for (int r = 0; r < 3; r++) { for (int c = 0; c < 3; c++) {System.out.print(table[r][c]+" ");} System.out.println();}
		
		int numSwaps = 0;
		int numOverlap = 0;
		
		//check 1 vs 2 (or 0 vs 1)
		numOverlap = Math.min(table[0][1], table[1][0]);
		table[0][0] += numOverlap;
		table[1][1] += numOverlap;
		table[0][1] -= numOverlap;
		table[1][0] -= numOverlap;
		numSwaps+=numOverlap;
		
		//check 1 vs 3 (or 0 vs 2)
		numOverlap = Math.min(table[0][2], table[2][0]);
		table[0][0] += numOverlap;
		table[2][2] += numOverlap;
		table[0][2] -= numOverlap;
		table[2][0] -= numOverlap;
		numSwaps+=numOverlap;
		
		//check 2 vs 3 (or 1 vs 2)
		numOverlap = Math.min(table[1][2], table[2][1]);
		table[1][1] += numOverlap;
		table[2][2] += numOverlap;
		table[1][2] -= numOverlap;
		table[2][1] -= numOverlap;
		numSwaps+=numOverlap;
		
		//System.out.println("\n" + numSwaps+"\nAfter:");
		//for (int r = 0; r < 3; r++) { for (int c = 0; c < 3; c++) {System.out.print(table[r][c]+" ");} System.out.println();}
		
		int numWrong = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (row == col) continue;
				numWrong+=table[row][col];
			}
		}
		
		numSwaps += 2 * numWrong / 3;
		
		out.println(numSwaps);
		out.close();
		System.exit(0);
	}
}