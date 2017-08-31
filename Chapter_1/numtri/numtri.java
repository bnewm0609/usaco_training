/*
ID: cbnewma1
LANG: JAVA
TASK: numtri
*/
import java.util.*;
import java.io.*;
class numtri {
	static int[][] nums;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		StringTokenizer st;
		int numrows = Integer.parseInt(f.readLine());
		nums = new int[numrows][];
		for (int i = 0; i < numrows; i++){
			nums[i] = new int[i+1];
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j <= i; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int largestSum = getLargestSum(numrows-2);
		out.println(largestSum);
		out.close();
		System.exit(0);
		//debugging print out
		//for (int[] is: nums) { for (int i: is) { System.out.print(i + "  ");}System.out.println();}
	}
	
	static int getLargestSum(int rownum) {
		for (int col = 0; col <= rownum; col++) {
			nums[rum][col] += Math.max(nums[rownum+1][col], nums[rownum+1][col+1]);
		}
		if (rownum <= 0) {
			return nums[0][0];
		} else {
			return getLargestSum(rownum-1);
		}
	}
}