/*
ID: cbnewma1
LANG: JAVA
TASK: runround
*/
import java.util.*;
import java.io.*;

public class runround {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		
		long num = Long.parseLong(f.readLine());
		long tempNum = num;
		int numLen;
		
		
		int[] map;// = new int[10];
		//int[] numArr = new int[10];
		//Arrays.fill(map, -1);
		//Arrays.fill(numArr, -1);
		//System.out.println(isRound(num, numLen));
		while(true) {
			num = tempNum + 1;
			numLen=(""+num).length();
			
			if (!isCandidate(num, numLen)) {
				//System.out.println(num + " is not a candidate");
				tempNum++;
				continue;
			}
			tempNum = num;
			//System.out.println(tempNum);

			map = new int[10];
			for (int i = numLen-1; i >= 0; i--) {
				map[i] = (int)(i+num%10)%numLen;
				//tempNum += num%10*Math.pow(10, numLen-i-1);
				num/=10;

			}
			//System.out.println(tempNum);
		
			//for (int i = 0; i < numLen; i++) { System.out.print(map[i] + " ");}
			//System.out.println();
			if(isRound(map, numLen))
				break;
				
			
			
			
		}
		
		out.println(tempNum);
		out.close();
		System.exit(0);
	}
	
	static boolean isCandidate(long num, int maxLen) {
		boolean[] visited = new boolean[10];
		long temp = num;
		for (int i = 0; i < maxLen; i++) {
			if (visited[(int)num%10] || num%10==0) {
				num = temp;
				return false;
			}
			visited[(int)num%10] = true;
			num/=10;
		} 
		num = temp;
		return true;
	}
	
	static boolean isRound(int[] map, int maxLen) {
		int temp = 0;
		boolean[] visited = new boolean[maxLen];
		//visited[0] = true;
		int currLoc = 0;
		while (!visited[currLoc]) {
			visited[currLoc] = true;
			temp++;
			currLoc = map[currLoc];
		}
		return temp == maxLen && currLoc == 0;
	}
}