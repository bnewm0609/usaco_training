/*
ID: cbnewma1
LANG: JAVA
PROG: milk2
*/
import java.util.*;
import java.io.*;
class milk2{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		
		int length = Integer.parseInt(f.readLine());
		int maxTime = 0;
		int minTime = 1000000;
		int[] bTimes = new int[length];
		int[] eTimes = new int[length];
		StringTokenizer st = null;
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(f.readLine());
			bTimes[i] = Integer.parseInt(st.nextToken());
			if (bTimes[i] < minTime) {
				minTime = bTimes[i];
			}
			eTimes[i] = Integer.parseInt(st.nextToken());
			if (eTimes[i] > maxTime) {
				maxTime = eTimes[i];
			}
		}
		
		String res = findMaxMilkTime(minTime, maxTime, bTimes, eTimes);
		//System.out.println(res);
		out.println(res);
		out.close();
		System.exit(0);
	}
	
	static String findMaxMilkTime(int minTime, int maxTime, int[] begins, int[] ends) {
		boolean[] timeline = new boolean[maxTime+1];
		for (int i = 0; i < begins.length; i++){
			for (int j = begins[i]; j < ends[i]; j++) {
				timeline[j] = true;
			}
		}
		
		int maxMilk = 0;
		int milkCount = 0;
		
		int maxNoMilk = 0;
		int noMilkCount = 0;
		//System.out.println(timeline.length);
		for (int k = minTime; k < timeline.length; k ++) {
			if (timeline[k]) {
				if (milkCount == 0) {
					milkCount++;
					if (noMilkCount > maxNoMilk) maxNoMilk = noMilkCount;
					noMilkCount = 0;
				} else {
					milkCount++;
				}
			} else {
				if (noMilkCount == 0) {
					noMilkCount++;
					if (milkCount > maxMilk) maxMilk = milkCount;
					milkCount = 0;
				} else {
					noMilkCount++;
				}
			}
		}
		return maxMilk + " " + maxNoMilk;
	}
}