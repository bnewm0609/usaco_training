/*
ID: cbnewma1
LANG: JAVA
PROG: namenum
*/
import java.util.*;
import java.io.*;

class namenum {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		
		String cNums = f.readLine();
		int cowNameLen = cNums.length();

		
		
		BufferedReader fDict = new BufferedReader(new FileReader("dict.txt"));
		//String[] nams = new String[4617];
		boolean namesPresent = false;
		for (int i = 0; i < 4617; i++) {
			String tempName = fDict.readLine();
			if (tempName.length() == cowNameLen) {
				if (toNums(tempName).equals(cNums)) {
					namesPresent = true;
					out.println(tempName);
					System.out.println(tempName);
				}
				//System.out.println(nams[counter]);
				//counter++;
			}
		}
		if (!namesPresent) {
			out.println("NONE");
			System.out.println("NONE");
		}
		out.close();
		System.exit(0);
		
	}
	
	static String toNums(String name) {
		HashMap<Character, Character> pad = new HashMap<Character, Character>();
		pad.put('A', '2');
		pad.put('B', '2');
		pad.put('C', '2');
		pad.put('D', '3');
		pad.put('E', '3');
		pad.put('F', '3');
		pad.put('G', '4');
		pad.put('H', '4');
		pad.put('I', '4');
		pad.put('J', '5');
		pad.put('K', '5');
		pad.put('L', '5');
		pad.put('M', '6');
		pad.put('N', '6');
		pad.put('O', '6');
		pad.put('P', '7');
		pad.put('R', '7');
		pad.put('S', '7');
		pad.put('T', '8');
		pad.put('U', '8');
		pad.put('V', '8');
		pad.put('W', '9');
		pad.put('X', '9');
		pad.put('Y', '9');
		//to catch nulls
		pad.put('Z', '0');
		pad.put('Q', '0');
		
		char[] nameArr = name.toCharArray();
		char[] result = new char[nameArr.length];
		for (int i = 0; i < nameArr.length; i++) {
			result[i] = pad.get(nameArr[i]);
		}
		
		return new String(result);
	}
}