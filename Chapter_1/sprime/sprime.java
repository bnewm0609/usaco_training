/*
ID: cbnewma1
LANG: JAVA
TASK: sprime
*/
import java.util.*;
import java.io.*;
class sprime {
	
	static int[] otherSet = {1, 3, 7, 9};
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
	
		int length = Integer.parseInt(f.readLine());
		int[] firstSet = {2, 3, 5, 7};
		
		
		printSuperPrimesFirst(out, length, firstSet, 0, 0);
		out.close();
		System.exit(0);
	}
	
	static void printSuperPrimesFirst(PrintWriter out, int len, int[] set, int currNum, int currLength) {
		currNum *= 10;
		for (int i: set) {
			currNum += i;
			if (isPrime(currNum)) {
				if (currLength + 1 == len) {
					out.println(currNum);
					//return;
				} else {
					printSuperPrimesFirst(out, len, otherSet, currNum, currLength+1);
				}
			}
			currNum -= i;
		}
	}
	
	static boolean isPrime(int num) {
		if (num < 10) {
			return true;
		} else if (num%2 == 0 || num%3 == 0) {
			return false;
		} else {
			for (int i = 5; i * i <= num; i+=6) {
				if (num%i==0 || num%(i+2)==0) {
					return false;
				}
			}
			return true;
		}
	}
}