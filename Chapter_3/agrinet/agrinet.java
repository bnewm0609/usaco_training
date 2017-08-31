/*
ID: cbnewman1
LANG: JAVA
TASK: agrinet
*/
import java.util.*;
import java.io.*;

public class agrinet {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		
		int max_dist = 100000;
		
		int n = Integer.parseInt(f.readLine());
		int[][] graph = new int[n][n];
		int[] minDists = new int[n];
		boolean[] inTree = new boolean[n];
		Arrays.fill(minDists, max_dist+1);
		StringTokenizer st;
		int counter = 0;
		
		while (counter < n*n) {
			st = new StringTokenizer(f.readLine());
			while (st.hasMoreTokens()) {
				graph[counter/n][counter%n] = Integer.parseInt(st.nextToken());
				counter++;
			}
		}
		
		
		//start minimum spanning tree algorithm
		int totalCable = 0;
		int currFarm = 0;
		int closest = 0;	
		int connectedFarms = 1;
		
		for (int i = 1; i < n; i++) {
			minDists[i] = graph[currFarm][i];
		}
		inTree[0] = true;
		
		while (connectedFarms < n) { //include all the farms
			//find closest farm not in tree
			closest = -1;
			for (int i = 0; i < n; i++) {
				if (inTree[i]) {
					continue;
				}
				
				if (closest == -1 | minDists[i] < minDists[closest]) { // -1 is first time through
					closest = i;
				}
				
			}
			
			connectedFarms++;
			totalCable += minDists[closest];
			inTree[closest] = true;
			for (int i = 0; i < n; i++) {		
				if (i == closest)
					continue;
				minDists[i] = Math.min(graph[closest][i], minDists[i]);
			}
			
			
			currFarm = closest;
		}
		
		out.println(totalCable);
		out.close();
	}
}