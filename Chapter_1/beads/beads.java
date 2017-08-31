/*
ID: cbnewma1
LANG: JAVA
PROG: beads
*/
import java.util.*;
import java.io.*;
class beads{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		
		int length = Integer.parseInt(f.readLine());
		char[] necklace = f.readLine().toCharArray();
		char[] twotimes = new char[2*necklace.length];
		for (int i = 0; i < twotimes.length; i++){
			twotimes[i] = necklace[i%necklace.length];
		}
		int longestString = longestString(twotimes, length);
		System.out.println(longestString);
		out.println(longestString);
		out.close();
		//System.exit(0);
	}
	
	static int longestString(char[] s, int threshold) {
		if (s.length == 6) return 3;
		char lastColorChar = ' ';
		char lastChar = s[0];
		int currRun = 1;
		int lastRun = 0;
		int maxBeads = 0;
		int whiteRun = lastChar=='w'?1:0;
		for (int i = 1; i < s.length; i++) {
			if (s[i] != lastChar){
				 
				 if (s[i] == 'w') {
				 	lastColorChar = lastChar;
				 	whiteRun = 1;
				 	currRun++;
				 	lastChar = s[i];
				 } else {
				 	
				 	
				 	if (lastRun + currRun> maxBeads){ 
				 		maxBeads = lastRun + currRun;
				 	}
//made change here!!!				 	
				 	if (s[i] == lastColorChar && whiteRun > 0) {
				 		//if (i < 10) System.out.println("last color part");
				 		currRun++;
				 		whiteRun = 0;
				 	} else {
				 		//if (i < 10) System.out.println("I'm here");
						lastRun = currRun - whiteRun;
						currRun = whiteRun + 1;
						if (lastChar != 'w') whiteRun = 0;
						lastColorChar = s[i];
					}
					lastChar = s[i];
				}
			} else {
				lastChar = s[i];
				if (s[i] == 'w') {
					whiteRun++;
					currRun++;
				}else{
					lastColorChar = s[i];
					currRun++;
				}
			}
			//System.out.println((i+1) + ") " + s[i] + ", maxBeads: " + maxBeads + ", currRun: " + currRun + ", lastRun: " + lastRun + ", whiteRun: "+ whiteRun);
		}
		if (maxBeads == 0) {
			maxBeads = currRun/2;
		}
		if (maxBeads > threshold) {
			maxBeads = longestString(Arrays.copyOfRange(s, 0, s.length/2 + 1), threshold);
		}
		return maxBeads;
	}
}
