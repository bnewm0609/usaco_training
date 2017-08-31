import java.util.*;
import java.io.*;

public class skidesign2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		
		int totalHills = Integer.parseInt(f.readLine());
		int[] startHeight = new int[totalHills];
		int[] currHeight = new int[totalHills];
		for (int i = 0; i < totalHills; i++) {
			startHeight[i] = Integer.parseInt(f.readLine());
			currHeight[i] = startHeight[i];
		}
		
		int minPrice = calcMinPrice(startHeight, currHeight);
		out.println(minPrice);
		out.close();
		System.exit(0);
	}
	
	public static int calcMinPrice(int[] startHeight, int[] currHeight) {
		int numRepsMin = 0;
		int numRepsMax = 0;
		int maxHeight = 0;
		int minHeight = 101;
		int[] minIndeces = new int[startHeight.length];
		int[] maxIndeces = new int[startHeight.length];
		
		for (int i = 0; i < startHeight.length; i++) {
			if (currHeight[i] < minHeight){
				minHeight = currHeight[i];
				numRepsMin = 0;
				minIndeces[numRepsMin] = i;
			} else if (currHeight[i] == minHeight) {
				minIndeces[++numRepsMin] = i;
			}
			if (currHeight[i] > maxHeight) {
				maxHeight = currHeight[i];
				numRepsMax = 0;
				maxIndeces[numRepsMax] = i;
			} else if (currHeight[i] == maxHeight) {
				maxIndeces[++numRepsMax] = i;
				//System.out.println(numRepsMax);
			}
		}
		
		if (currHeight[maxIndeces[0]] - currHeight[minIndeces[0]] <= 17) {
			System.out.println("HOPIDJFSOIJFOIDJF");
			return getAllCost(startHeight, currHeight);
		}
		
		// int totalCostMin = 0;
// 		for (int i = 0; i <= numRepsMin; i++) {
// 			
// 			totalCostMin += getCost(startHeight[minIndeces[i]], currHeight[minIndeces[i]] + 1);
// 		}
// 		//max
// 		int totalCostMax = 0;
// 		for (int j = 0; j <= numRepsMax; j++) {
// 			totalCostMax += getCost(startHeight[maxIndeces[j]], currHeight[maxIndeces[j]] - 1);
// 		}
// 		
// 		if (totalCostMin > totalCostMax) {
// 			addToAll(currHeight, maxIndeces, numRepsMax, -1);
// 		} else {
// 			addToAll(currHeight, minIndeces, numRepsMin, 1);
// 		}
		
		//return Math.min(calcMinPrice(startHeight, addToAll(currHeight, maxIndeces, numRepsMax, -1)), calcMinPrice(startHeight, addToAll(currHeight, maxIndeces, numRepsMax, 1)));
		return calcMinPrice(startHeight, addToAll(currHeight, maxIndeces, numRepsMax, -1));
	}
	
	public static int[] addToAll(int[] nums, int[] indeces, int numOfIndeces, int toAdd) {
		for (int i = 0; i <= numOfIndeces; i++) {
			nums[indeces[i]] += toAdd;
		}
		return nums;
	}
	
	public static int getCost(int s, int c) {
		return (int) Math.pow(s - c, 2);
	}
	
	public static int getAllCost(int[] s, int[] c) {
		int total = 0;
		for (int i = 0; i < s.length; i++) {
			total += getCost(s[i], c[i]);
		}
		return total;
	}
}	