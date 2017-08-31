/*
ID: cbnewma1
LANG: JAVA
TASK: inflate
*/
import java.util.*;
import java.io.*;

public class inflate {

	//static boolean[] m;
	//static int maxP;
	//static HashMap<Integer, Integer> m; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int time = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] best= new int[10001];
		int t = 0; 
		int p = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			for (int j = 0; j+t <= time; j++) {
				if (best[j] + p > best[j+t]) {
					best[j+t] = best[j] + p;
				}
				//best[j+t] = Math.max(best[j] + p, best[j+t]);
			}
		}
		//int m = 0; 
		// for (int i = 0; i <= time; i++) {
// 			if (m <best[i]) {
// 				m = best[i];
// 			}
// 		}
		out.println(best[time]);
		out.close();
	}
		// int[] points = new int[n];
// 		int[] times = new int[n];
// 		int[] marginalPoints = new int[n];
// 		boolean[] consideredProblem = new boolean[n];
// 		
// 		int maxI = 0;
// 		int minTime = Integer.MAX_VALUE;
// 		for (int i = 0; i < n; i++) {
// 			st = new StringTokenizer(f.readLine());
// 			points[i] = Integer.parseInt(st.nextToken());
// 			times[i] = Integer.parseInt(st.nextToken());
// 			marginalPoints[i] = points[i]/times[i];
// 			
// 			if (marginalPoints[maxI] <= marginalPoints[i]) {
// 				if (marginalPoints[maxI] == marginalPoints[i]) {
// 					if (points[i] > points[maxI]) {
// 							maxI = i;
// 					}
// 				} else {
// 					maxI = i;
// 				}
// 			}
// 			
// 			if (times[i] < minTime) {
// 				minTime = times[i];
// 			}
// 		}
// 		System.out.println(minTime);
// 		
// 		// for (int i = 0; i < marginalPoints.length; i++) {
// // 			System.out.println("P: " + points[i] +"\tT: " + times[i] + "\tMP: " + marginalPoints[i]);
// // 		}
// 		
// 		consideredProblem[maxI] = true;
// 		//maxP = points[maxI]*((time/times[maxI]) + 1);
// 		//m = new boolean[points[maxI]*((time/times[maxI]) + 1)];
// 		
// 		//m = new HashMap<>();
// 		
// 		int res = calculateMaxPoints(times, points, marginalPoints, consideredProblem, time, maxI, minTime);
// 		out.println(res);
// 		out.close();
// 	}
// 	
// 	public static int calculateMaxPoints(int[] times, int[] points, int[] mps, boolean[] lookedAt, int lim, int maxIndex, int minTime) {
// 		//
// 		//System.out.println("Time Left = " + lim);
// 		if (lim < minTime) {
// 			return 0;
// 		}
// 		
// 		if (lim - times[maxIndex] >= times[maxIndex] || lim == minTime) {
// 			return points[maxIndex] + calculateMaxPoints(times, points, mps, lookedAt, lim - times[maxIndex], maxIndex, minTime);
// 		} else {
// 			//System.out.println("switching...");
// 			//find the highest marginal points
// 			int mpI = -1;
// 			double highestMP = -1;
// 			for (int i = 0; i < points.length; i++) {
// 				if (mps[i] > highestMP && !lookedAt[i] && times[i] <= lim) {
// 					if (mps[i] == highestMP) {
// 						if (points[i] > points[mpI]) {
// 							mpI = i;
// 							highestMP = mps[i];
// 						}
// 					} else {
// 						mpI = i;
// 						highestMP = mps[i];
// 					}
// 				}
// 			}
// 			
// 			if (mpI == -1) { //we've looked at all the other choices --> we're at the last chioce
// 				//System.out.println("we haven't changed anything");
// 				return points[maxIndex];
// 			}
// 			
// 			lookedAt[mpI] = true;
// 			
// 			int maxPointsSame = 0;
// 			if (lim > times[maxIndex])
// 				maxPointsSame = points[maxIndex] + calculateMaxPoints(times, points, mps, lookedAt, lim - times[maxIndex], mpI, minTime);
// 			
// 			int maxPointsOther = calculateMaxPoints(times, points, mps, lookedAt, lim, mpI, minTime);
// 
// 			//System.out.println("Same max points: " + maxPointsSame +"\tOther max points: "+ maxPointsOther);
// 			lookedAt[mpI] = false;
// 			return Math.max(maxPointsSame, maxPointsOther);
// 		}
// 	}
}










