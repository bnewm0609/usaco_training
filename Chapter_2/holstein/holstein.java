/*
ID: cbnewma1
LANG: JAVA
TASK: holstein
*/
import java.util.*;
import java.io.*;

public class holstein {

	//static boolean[] usedResult;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		StringTokenizer st;
		
		int numVitamins = Integer.parseInt(f.readLine());
		int[] vitamins = new int[numVitamins];
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < numVitamins; i++) {
			vitamins[i] = Integer.parseInt(st.nextToken());
		}
		
		int numFeeds = Integer.parseInt(f.readLine());
		int[][] feedTable = new int[numFeeds][numVitamins];
		for (int row = 0; row < numFeeds; row++) {
			st = new StringTokenizer(f.readLine());
			for (int col = 0; col < numVitamins; col++) {
				feedTable[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		//boolean[] usedResult = new boolean[numFeeds];
		int[] result = new int[numFeeds];
		int[] tempVits = new int[numVitamins];
		for (int i = 1; i <= numFeeds && !findFeed(0, i, tempVits, vitamins, result, feedTable); i++){}//System.out.println();}
		//System.out.println("hi");
		//Arrays.sort(result);
		int sum = 0;
		//int[] finRes = new int[numFeeds];
		for (; sum < result.length && result[sum] != 0; sum++) {}
		//System.out.print(result[sum] + " ");}
		out.print(sum);
		for (int i = 0; i < sum; i++) {
			out.print(" " + result[i]);
		}
		out.println();
		out.close();
		System.exit(0);
	}
	
	static boolean findFeed(int currIt, int length, int[] tempVits, int[] trueVits, int[] result, int[][] feedTable) {
		if (currIt == length) {
			//System.out.println("I'm done");
			return allGreaterThan(tempVits, trueVits);
		}
		
		
		//if (rowSum(tempVits) >  rowSum(trueVits)) return;
		
		int i;
		if (currIt == 0) {
			i = 0;
		} else {
			i = result[currIt - 1] - 1;
		}
		//System.out.println("current iteration: " + currIt + "\ti = " + (i));
		for (; i < feedTable.length; i++) {
			//System.out.println(i);
			if (i == 6) {
				//System.out.println("\tBEFORE: current iteration: " + currIt + "\ti+1 = " + (i));
				
			}
			
			//System.out.print("result: ");
			//for (int j = 0; j <  result.length; j++) {System.out.print(" " + result[j]);}
			//System.out.println();
			if (includes(i+1, result)) {
				//System.out.print("continuing: ");
				//System.out.println("current iteration: " + currIt + "\ti+1 = " + (i+1));
				continue;
			}
			//if (usedResult[i]) continue;
			
			
			//usedResult[i] = true;
			result[currIt] = i+1;
			rowAdd(tempVits, feedTable[i]);
			if (findFeed(currIt+1, length, tempVits, trueVits, result, feedTable)) {
				//System.out.println("returning true");
				return true;
			}
			result[currIt] = 0;
			//usedResult[i] = false;
			rowSubtract(tempVits, feedTable[i]);
		}
		//System.out.println("returning false");
		return false;
	}
	
	static boolean includes(int i, int[] arr) {
		for (int a : arr) {
			if (a == i) return true;
			if (a == 0) return false;
		}
		return false;
	}
	
	static void rowAdd(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] += arr2[i];
		}
	}
	
	static void rowSubtract (int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] -= arr2[i];
		}
	}
	
	static boolean allGreaterThan(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] < arr2[i]) return false;
		}
		return true;
	}
}