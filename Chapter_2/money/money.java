/*
ID: cbnewma1
LANG: JAVA
TASK: money
*/
import java.util.*;
import java.io.*;

public class money {
	
	static int[] coins;
	static long numWays = 0;
	static long[][] table;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numCoins = Integer.parseInt(st.nextToken());
		int totMoney = Integer.parseInt(st.nextToken());
		table = new long[numCoins][totMoney+1];
		
		coins = new int[numCoins];
		
		String input = "";
		String line;
		while ((line = f.readLine()) != null) {
			input += line + " ";
		}
		st = new StringTokenizer(input);
		for (int i = 0; i < numCoins; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(coins);
		
		countWays(0, coins.length-1, totMoney);
		
		out.println(numWays);
		//System.out.println(numWays);
		out.close();
		System.exit(0);
	}
	
	static void countWays(int count, int maxIndex, int total) {
		for (int i = maxIndex; i >= 0; i--) {
			if (table[i][total-count] == 0){
				long startCount = numWays;			
				if (count+coins[i] < total) {
					countWays(count+coins[i], i, total); 
				} else if (count+coins[i] == total) {
					numWays++;
				}
				table[i][total-count] = (numWays-startCount);
			} else {
				numWays+=table[i][total-count];
			}
		}
	}
	
}