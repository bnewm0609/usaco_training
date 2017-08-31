/*
ID: cbnewma1
LANG: JAVA
TASK: combo
*/
import java.util.*;
import java.io.*;
class combo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		int maxDial = Integer.parseInt(f.readLine());
		int[] farmerCombo = new int[3];
		int[] masterCombo = new int[3];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++){
			farmerCombo[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {
			masterCombo[i] = Integer.parseInt(st.nextToken());
		}
		
		int sol = solve(farmerCombo, masterCombo, maxDial);
		//System.out.println(sol);
		out.println(sol);
		out.close();
		System.exit(0);
	}
	
	static int solve(int[] combo1, int[] combo2, int maxDial) {
		int difference = 0;
		int total = 0;
		for (int i = 0; i < 3; i++) {
			if (combo1[i] - 2 <= 1 || maxDial - combo1[i] <= 1 || combo2[i] - 2 <= 1 || maxDial - combo2[i] <= 1) {
				//we are at the edge of the dial, where the numbers restart
				difference = Math.min(Math.min(
					Math.abs(combo1[i] - combo2[i]), Math.abs(combo1[i] + maxDial - combo2[i])),
					Math.abs(combo2[i] + maxDial - combo1[i]));
			} else {
				difference = Math.abs(combo1[i] - combo2[i]);
			}
			//System.out.println(difference);
			if (difference <= 4) {
				if (total == 0) total = 1;
				
				total *= 5 - difference;
			}
		}
		return (int) Math.min(250 - total, Math.pow(maxDial, 3));
	}
}