/*
ID: cbnewma1
LANG: JAVA
PROG: ride
*/
import java.util.*;
import java.io.*;
class ride {
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		
		char[] comet = f.readLine().trim().toCharArray();
		char[] group = f.readLine().trim().toCharArray();
		int cometP = 1;
		int groupP = 1;
		//A in ASCII? is 65
		for (int i = 0; i < comet.length; i++){
			//System.out.println(comet[i]+": "+(comet[i]-64));
			cometP *= comet[i]-64;
		}
		for (int i = 0; i < group.length; i++){
			//System.out.println(group[i]+": "+(group[i]-64));
			//System.out.println(groupP);
			groupP *= group[i]-64;
		}
		//System.out.println("comet: " + cometP + ", " + (cometP % 47) + " group: " +groupP + ", "+ (groupP%47));
		if (cometP % 47 == groupP % 47) {
			//System.out.println("GO");
			out.println("GO");
		} else {
			//System.out.println("STAY");
			out.println("STAY");
		}
		out.close();
		System.exit(0);
	}
}