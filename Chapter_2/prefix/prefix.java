/*
ID: cbnewma1
LANG: JAVA
TASK: prefix
*/
import java.util.*;
import java.io.*;

public class prefix {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		String[][] prefixes = new String[10][200];
		int[] lengths = new int[10];
		String prefix;
		int max = 0;
		while (true) {
			try {
				prefix = st.nextToken();
			} catch (Exception e) {
				st = new StringTokenizer(f.readLine());
				continue;
			}
			
			if (prefix.equals(".")) {
				break;
			}
			if (prefix.length() >  max)
				max = prefix.length();
			prefixes[prefix.length()-1][lengths[prefix.length()-1]++] = prefix;
		}
		// System.out.println(max);
// 		for(int i = 0; i < 10; i++) {
// 			for (int j = 0; prefixes[i][j] != null; j++) {
// 				System.out.print(prefixes[i][j] + " ");
// 			}
// 			System.out.println();
// 		}
		
		StringBuffer sequenceB = new StringBuffer();
		String seq;
		while (true) {
			seq = f.readLine();
			if (seq == null)
				break;
			sequenceB.append(seq);
		}
		//System.out.println("Done reading");
		out.println(getMaxPrefix(prefixes, sequenceB.toString(), max));
		out.close();
		System.exit(0);
		//System.out.println(sequence);
	}
	
	static int getMaxPrefix(String[][] prefixes, String sequence, int max) {
		max--;
		boolean[] sequenceBools = new boolean[sequence.length()];
		int i = 0, j = 0;
		int numsConsFalse = 0;
		while (true) {
			
			if (contains(prefixes[j-i], sequence.substring(i, j+1))) {
				//System.out.println("hi!");
				if (i == 0 || sequenceBools[i-1]) {
					sequenceBools[j] = true;
					i = ++j;
					if (j == sequence.length())
						return j;
					numsConsFalse = 0;
				} else {
					//START: same as below 
					numsConsFalse++;
					if (numsConsFalse <= max) {
						j++;
						if (j == sequence.length())
							return j-1;
						i = j - numsConsFalse;
					} else {
						//System.out.println("j: " + j);
						return j - max;
					}
					//END
				}
			} else {
				if (j-i >= max || i == 0) {
					//START: same as above
					numsConsFalse++;
					if (numsConsFalse <= max) {
						j++;
						if (j == sequence.length())
							return j - 1;
						i = j - numsConsFalse;
					} else {
						//System.out.println("2j: " + j);
						return j - max;
					}
					//END
				} else {
					i--;
				}
			}
		}
	}
	
	static boolean contains (String[] prefixes, String search) {
		for (String s: prefixes) {
			if (search.equals(s)) {
				return true;
			}
		}
		return false;
	}
}