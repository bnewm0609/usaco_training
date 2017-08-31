/*
ID: cbnewma1
LANG: JAVA
PROG: dualpal
*/
import java.util.*;
import java.io.*;

class dualpal{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken()) + 1;
		
		HashMap<Integer, Character> bases = new HashMap<Integer, Character>();
		for (int i = 0; i < 20; i++) {
			bases.put(i, Character.forDigit(i, 20));
		}
		
		int ndPalsFound = 0;
		while (ndPalsFound < n) {
			int nPals = 0;
			if (isPalindrome(Integer.toString(s))) {
				nPals++;
			}
			
			for (int i = 2; i < 10; i++) {
				if (isPalindrome(convertBase(s, i, bases))) {
					nPals++;
					if (nPals == 2) {
						ndPalsFound++;
						out.println(s);
						//System.out.println(s);
						break;
					}
				}
			}
			s++;
		}
		
		out.close();
		System.exit(0);
	}
	
	static String convertBase(int num, int base, HashMap<Integer, Character> symbs) {
		
		String cnvt = "";
		while (num > 0) {
			cnvt = symbs.get(num % base) + cnvt;
			num /= base;
		}
		
		return cnvt;
	}
	
	static boolean isPalindrome(String word) {
		char[] wrdArr = word.toCharArray();
		for (int i = 0; i <  (int) Math.ceil(wrdArr.length/2.0); i++) {
			if (wrdArr[i] != wrdArr[wrdArr.length - i - 1]) {
				return false;
			}
		}
		return true;
	}
}