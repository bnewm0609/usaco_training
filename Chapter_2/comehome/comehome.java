/*
ID: cbnewma1
LANG: JAVA
TASK: comehome
*/

import java.util.*;
import java.io.*;

public class comehome {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		
		int n = Integer.parseInt(f.readLine());
		
		boolean[] visitedEdges = new boolean[n];
		int[] nodes1 = new int[n];
		int[] nodes2 = new int[n];
		int[] weights = new int[n];
		
		boolean[] possibleNodes = new boolean[52];
		boolean[] cowAt = new boolean[52];
		int[] distFromSource = new int[52];
		for (int i = 1; i < 52; i++) {
			distFromSource[i] = Integer.MAX_VALUE;
		}
		
		
		StringTokenizer st;
		//setup part
		char a;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());	
			
			a = st.nextToken().charAt(0);
			if (Character.isUpperCase(a)) {
				cowAt[(a+1-'A')%26] = true;
				nodes1[i] = (a+1-'A')%26;
			} else {
				nodes1[i] = 26 + (a - 'a');
			}
			
			
			//possibleNodes[(a+1-'A')%26] = true;
			
			a = st.nextToken().charAt(0);
			if (Character.isUpperCase(a)) {
				cowAt[(a+1-'A')%26] = true;
				nodes2[i] = (a+1-'A')%26;
			} else {
				nodes2[i] = 26 + (a - 'a');
			}
			
			//possibleNodes[(a+1-'A')%26] = true;
			
			weights[i] = Integer.parseInt(st.nextToken());
			//System.out.print(nodes1[i] + " ");
		}
		
		//System.out.println();
		
		cowAt[0] = false;
		possibleNodes[0] = false;
		
		// for (int i = 0; i < cowAt.length; i++) {
// 			if (i == 0) {
// 				System.out.println((char) ('Z') +": " + cowAt[i] + " "); 	
// 			} else if (i < 26) {
// 				System.out.println((char) ('A' + i - 1) +": " + cowAt[i] + " "); 
// 			} else {
// 				System.out.println((char) ('a' + i - 26) +": " + cowAt[i] + " "); 
// 			}
// 			
// 		}
		
		
		//djikstra's part
		int min = 0;
		int minx = 0;
		int q = 0;
		while (!allVisited(visitedEdges)) {
			//find minimum of all that are connected to min
			
			//minx = min;
			for (int i = 0; i < n; i++) {
				if (!visitedEdges[i] && nodes1[i] == min) {
					q = nodes2[i];
				} else if (!visitedEdges[i] && nodes2[i] == min) {
					q = nodes1[i];
				} else {
					continue;
				}
				
				minx = q;
				//System.out.println(distFromSource[q] + "\t"+ (distFromSource[min] + weights[i]));
				distFromSource[q] = Math.min(distFromSource[min] + weights[i], distFromSource[q]);
				
				// if (distFromSource[minx] > distFromSource[q]) {
// 					minx = q;
// 				}
				possibleNodes[q] = true;
				visitedEdges[i] = true;
			}
			//new min is q
			min = minx;
			//check if there's a cow
			
			for (int i = 0; i < possibleNodes.length; i++) {
				if (possibleNodes[i]) {
					//System.out.print(distFromSource[i] + " ");
					if (distFromSource[i] < distFromSource[min]) {
						min = i;
						//minx = distFromSource[i];
					}
				}
			}
			
			if (cowAt[min]) {
			//if cow return letter and distFromSource
				out.println((char)('A' + min - 1) + " " + distFromSource[min]);
				//out.println(distFromSource[min]);
				out.close();
				System.out.println("returning early");
				return;
			}
			possibleNodes[min] = false;
			//if no cow go back to top 
		}
		
		int minD = Integer.MAX_VALUE;
		for (int i = 0; i < possibleNodes.length; i++) {
			if (possibleNodes[i] && cowAt[i]) {
				//System.out.print(distFromSource[i] + " ");
				if (distFromSource[i] < minD) {
					min = i;
					minD = distFromSource[min];
					//minx = distFromSource[i];
				}
			}
		}
		//System.out.println(min);
		out.println((char)('A' + min - 1) + " " + distFromSource[min]);
		//out.println(distFromSource[min]);
		out.close();
	}
	
	private static boolean allVisited(boolean[] v) {
		 for (int i = v.length-1; i >= 0; i--) {
		 	if (!v[i]) {
		 		//System.out.println(i);
		 		return false;
		 	}
		 }
		 return true;
	}
	
}