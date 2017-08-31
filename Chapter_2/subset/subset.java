/*
ID: cbnewma1
LANG: JAVA
TASK: subset
*/
import java.util.*;
import java.io.*;

public class subset {

	//static int maxTotalSum = 20*39;
	//static int unused = 0;
	static int[] sums;
	static int[][] numbers_sums;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int n = Integer.parseInt(f.readLine());
		int total_sum = n*(n+1) / 2;
		int wanted_sums = total_sum/2;
		int combos = 0;
		
		//System.out.println(wanted_sums);
		if (total_sum % 2 == 0) {
			sums = new int[wanted_sums+1];
			numbers_sums = new int[n+1][wanted_sums+1];
			
			for (int i = n; i > 2; i--) {
				combos += sums[wanted_sums-i];
				update(i, n, wanted_sums);
				//System.out.println("\n" + i + ": " + combos);
				//System.out.println(sums[11]);
				//System.out.println("4.3: " + numbers_sums[4][2]);
				//for (int d: sums) {System.out.print(d+" ");}
			}
			
			combos += sums[wanted_sums-2];
		}
		
		//combos += sums[wanted_sums - 1];
		//System.out.println("\n" + 2 + ": " + combos);
		//combos /= 2;
		
		out.println(combos);
		out.close();
		System.exit(0);
		
	}
	
	static void update(int x, int n, int ws) {
		sums[x]++;
		//numbers_sums[x] = new int[(int)Math.pow(2, n-x)+1-unused];
		//unused = 0;
		numbers_sums[x][x]++;
		for (int j = x + 1; j <= n; j++) {
			for (int k = j; k <= ws; k++) {
				if (x + k >= ws) {
					break;
				} else if (numbers_sums[j][k] != 0) {
					//int a = x + k*numbers_sums[j][k];
					numbers_sums[x][x + k] += numbers_sums[j][k];
					sums[x+k] += numbers_sums[j][k];
					// if (x == 4) {
// 						System.out.print("\nnumbers_sums[4]["+i+"] = " + numbers_sums[4][i]);//+ x + " + " + numbers_sums[j][k]);
// 						System.out.print("\nsums[numbers_sums[4]["+i+"]] = " + sums[numbers_sums[x][i]]);
// 					}
// 					if (x == 3 && j == 4) {
// 						System.out.print("\n"+j + ", " + k + ", numbers_sums[4]["+k+2+"]: " + numbers_sums[j][k+2]);
// 					}
					
				}
				
			}
		}
	}		
}
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		// if (total_sum % 2 == 0) {
// 		
// 			int bs = 0; int j = n;
// 			while (bs + j <= wanted_sums) {
// 				bs += j;
// 				j--;
// 			}
// 			int lowerBound = n - j + 1;
// 			lowerBound = 0;
// 			bs = 0; j = 0;
// 			while (bs + j <= wanted_sums) {
// 				bs += j;
// 				j++;
// 			}
// 			int upperBound = lowerBound + (j-1-lowerBound)/2;
// 			upperBound = 15;
// 			System.out.println(lowerBound);
// 			System.out.println(upperBound);
// 			
// 		
// 			combos = 0;
// 			for (int i = lowerBound; i <= upperBound; i++) {
// 				combos += sumOf(1, n, i, 0, wanted_sums/2);
// 				//System.out.println(combos);
// 			}
// 			
// 			int total_choices = chooseTwo(combos);
// 			int totalOverlap = 0;
// 			for (int i = 1; i <= n; i++) {
// 				System.out.println(nums[i]);
// 				totalOverlap+= chooseTwo(nums[i]);
// 			}
// 			combos = total_choices - totalOverlap;
// 			//combos/=2;
// 		}
// 		
// 		
// 		
// 	}
// 	
// 	
// 	static int sumOf(int start, int end, int count, int curr_sum, int want_sum) {
// 		if (count == 0) {
// 		
// 			if (curr_sum == want_sum) {
// 				nums[start-1]++;
// 				return 1;
// 			} else
// 				return 0;
// 			//return curr_sum == want_sum ? 1 : 0;
// 		}
// 		// if (count == 1) {
// // 			int needed_val = want_sum - curr_sum;
// // 			return end >= needed_val && needed_val >= start ? 1:0;
// //  		}
// 		
// 		int x = 0;
// 		int t = 0;
// 		for (int i = start; i <= end + 1 - count; i++, t=0) {
// 			if (curr_sum + i > want_sum)
// 				break;
// 				
// 			t = sumOf(i + 1, end, count - 1, curr_sum + i, want_sum);
// 			if (t > 0) {
// 				x += t;
// 				nums[i]++;
// 			}
// 			
// 		}
// 		return x;
// 	}
// 	
// 	static int chooseTwo(int a) {
// 		return a*(a-1)/2;
// 	}
// 	static int factorial(int a) {
// 		return a < 2 ? 1 : a*factorial(a-1);
// 	}
//}