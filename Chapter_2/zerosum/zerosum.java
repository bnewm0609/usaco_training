/*
ID: cbnewa1
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
	
	//public int[][] sums;
	static PrintWriter out;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		int n = Integer.parseInt(f.readLine());
		
		getAnswer(2, n+1, 1, 1, "1", 'p');
		out.close();
		System.exit(0);
	}
	
	static void getAnswer(int currVal, int finalVal, int currSum, int numBlanks, String history, char lastAct) {
		if (currVal == finalVal) {
			//System.out.println(history + "\t"+currSum);
			if (currSum == 0) {
				out.println(history);
				//System.out.println(history);
			}
			return;
		}
		
		//blanks
		int joinedNum = 0;
		for (int i = 1; i <= numBlanks; i++) {
			joinedNum+=(currVal-i) * (int)Math.pow(10, i-1);
		}
		if (lastAct == 'p') {
			getAnswer(currVal+1, finalVal, currSum + joinedNum*9 + currVal, numBlanks+1, history + " " + currVal, lastAct);
		} else {
			getAnswer(currVal+1, finalVal, currSum - joinedNum*9 - currVal, numBlanks+1, history + " " + currVal, lastAct);
		}
		//plus
		getAnswer(currVal+1, finalVal, currSum+currVal, 1, history+"+"+currVal, 'p');
		//minus
		getAnswer(currVal+1, finalVal, currSum-currVal, 1, history+"-"+currVal, 'm');
	}
}