/*
ID: cbnewma1
LANG: JAVA
PROG: palsquare
*/
import java.util.*;
import java.io.*;
class palsquare {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		
		HashMap<Integer, Character> bases = new HashMap<Integer, Character>();
		for (int i = 0; i < 20; i++) {
			bases.put(i, Character.forDigit(i, 20));
		}
		
		//isPalindrome("helot");
		//isPalindrome("shelehs");
		//System.out.println();
		//isPalindrome("quaauq");
		
		
		int base = Integer.parseInt(f.readLine());
		for (int i = 1; i <= 300; i++) {
			int sqr = (int) Math.pow(i, 2);
			String sqrBase = convertToBase(sqr, base, bases);
			if (isPalindrome(sqrBase)) {
				String num = convertToBase(i, base, bases);
				//System.out.println(num + " " + sqrBase);
				out.println(num + " " + sqrBase);
			}
		}
		out.close();
		System.exit(0);
		//*/
	}
	
	static String convertToBase(int num, int base, HashMap<Integer, Character> symbs) {
		
		String conv = "";
		while (num > 0) {
			conv = symbs.get(num % base) + conv;
			num = num/base;
		}
		return conv.toUpperCase();
	}
	
	static boolean isPalindrome(String word) {
		char[] wordArr = word.toCharArray();
		for (int i = 0; i < (int) Math.ceil(wordArr.length/2.0); i++) {
			//System.out.println(wordArr[i] + " : " + wordArr[wordArr.length - i - 1]);
			if (wordArr[i] != wordArr[wordArr.length - i - 1]) {
				return false;
			}
		}
		return true;
	}
}