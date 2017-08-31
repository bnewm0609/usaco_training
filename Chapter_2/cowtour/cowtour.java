/*
ID: cbnewma1
LANG: JAVA
TASK: cowtour
*/
import java.util.*;
import java.io.*;

class cowtour {


	static int[] paths;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		StringTokenizer st;
		 
		//reading the input
		int n = Integer.parseInt(f.readLine());
		int[] xs = new int[n];
		int[] ys = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
		}
		
		double[][] adjMat = new double[n][n];
		char[] nums;
		for (int i = 0; i < n; i++) {
			nums = f.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				adjMat[i][j] = Integer.parseInt(""+nums[j]);
			}
		}
		
		//find the connected paths
		paths = new int[n];
		int curpath = 0;
		
		int first = stillUnlabeled(paths);
		while (first != -1) {
			labelPath(++curpath, first, adjMat);
			first = stillUnlabeled(paths);
		}
		
		// for (int i = 0; i < paths.length; i++) {
// 			System.out.print(paths[i] + " ");
// 		}
		
		//reformat data
		//int[][] xsp = new int[curpath][];
		//int[][] ysp = new int[curpath][];
		double[] diams = new double[curpath];
		int[][] inds = new int[curpath][];
		double[][] dists = new double[curpath][];
		for (int i = 1; i <= curpath; i++) {
			int num = 0;
			for (int j = 0; j < n; j++) {
				if (paths[j] == i) {
					num++;
				}
			}
			//xsp[i-1] = new int[num];
			//ysp[i-1] = new int[num];
			inds[i-1] = new int[num];
			dists[i-1] = new double[num];
		}
		
		for (int i = 1; i <= curpath; i++) {
			int num = 0;
			for (int j = 0; j < n; j++) {
				if (paths[j] == i) {
					//xsp[i-1][num] = xs[j];
					//ysp[i-1][num] = ys[j];
					inds[i-1][num] = j;
					num++;
				}
			}
		}
		
		
		
		
		//Floyd-warshall
		for (int p = 0; p < curpath; p++) {
			for (int i = 0; i < inds[p].length; i++) {
				for (int j = 0 ; j <= i; j++) {
					if (adjMat[inds[p][i]][inds[p][j]] > 0) {
						adjMat[inds[p][i]][inds[p][j]] = Math.sqrt((xs[inds[p][i]] - xs[inds[p][j]])* (xs[inds[p][i]] - xs[inds[p][j]]) + (ys[inds[p][i]] - ys[inds[p][j]])* (ys[inds[p][i]] - ys[inds[p][j]]));
						adjMat[inds[p][j]][inds[p][i]] = adjMat[inds[p][i]][inds[p][j]];
						//System.out.println(inds[p][i] + "\t" + inds[p][j] + "\t" + Math.sqrt((xs[inds[p][i]] - xs[inds[p][j]])* (xs[inds[p][i]] - xs[inds[p][j]]) + (ys[inds[p][i]] - ys[inds[p][j]])* (ys[inds[p][i]] - ys[inds[p][j]])));
					} else if (i != j) {
						adjMat[inds[p][i]][inds[p][j]] = Double.MAX_VALUE;
						adjMat[inds[p][j]][inds[p][i]] = Double.MAX_VALUE;
					}
				}
			}
			
			
		
			for (int i = 0; i < inds[p].length; i++) {
				for (int j = 0; j < inds[p].length; j++) {
					for (int k = 0; k <= j; k++) {
						//System.out.println(inds[p][k] + "\t" + inds[p][j]);
						if (adjMat[inds[p][j]][inds[p][k]] > adjMat[inds[p][i]][inds[p][k]] + adjMat[inds[p][i]][inds[p][j]]) {
							adjMat[inds[p][j]][inds[p][k]] = adjMat[inds[p][i]][inds[p][k]] + adjMat[inds[p][i]][inds[p][j]];
							adjMat[inds[p][k]][inds[p][j]] = adjMat[inds[p][j]][inds[p][k]];
							//if (p == 1) {
							//System.out.println(inds[p][k] + "\t" + inds[p][j] +"\t" +inds[p][i]);
							//}
						}
					}
				}
			}
			
			int max = 0;
			double diam = 0;
			for (int i = 0; i < inds[p].length; i++) {
				for (int j = 0; j < inds[p].length; j++) {
					if (adjMat[inds[p][i]][inds[p][j]] > adjMat[inds[p][i]][inds[p][max]]) {
						max = j;
					}
				}
				dists[p][i] = adjMat[inds[p][i]][inds[p][max]];
				if (dists[p][i] > diam) {
					diam = dists[p][i];
				}
				max = 0;
			}
			diams[p] = diam;
		}
		
		//print loop
		// for (int i = 0; i < curpath; i++) {
// 			for (int j = 0; j < inds[i].length; j++) {
// 				System.out.print(dists[i][j] + " ");
// 			}
// 			System.out.println();
// 		}
// 		
// 		//print loop
// 		for (int i = 0; i < n; i++) {
// 			for (int j = 0; j < n; j++) {
// 				System.out.printf("%.2f\t", adjMat[i][j]);
// 			}
// 			System.out.println();
// 		}

		
		
		double minDiameter = Integer.MAX_VALUE;
		//double diameter = 0;
		double pDist = 0;
		int c = 0;
		//Now loop through all unique subgraph pairs
		for (int p1 = 0; p1 < curpath; p1++) {
			for (int p2 = p1 + 1; p2 < curpath; p2++) {
				for (int i1 = 0; i1 < inds[p1].length; i1++) {
					for (int i2 = 0; i2 < inds[p2].length; i2++) {
						pDist = Math.sqrt((xs[inds[p1][i1]] - xs[inds[p2][i2]])*(xs[inds[p1][i1]] - xs[inds[p2][i2]]) + (ys[inds[p1][i1]] - ys[inds[p2][i2]])*(ys[inds[p1][i1]] - ys[inds[p2][i2]]));
						//System.out.println("count" + c++);
						// if (i1 == 2 && i2 == 1) {
// 								System.out.println(diameter + "\t" + inds[p1][i1]+ "\t" + inds[p2][i2] + "\tpDist: " +pDist);
// 								System.out.println(xs[inds[p1][i1]] + "\t" + ys[inds[p1][i1]] + "\t" + xs[inds[p2][i2]] + "\t" + ys[inds[p2][i2]]); 
// 							}
						if (minDiameter > dists[p1][i1] + pDist + dists[p2][i2]) {
							minDiameter = dists[p1][i1] + pDist + dists[p2][i2];
							
						}
					}
					// if (diameter < minDiameter) {
// 						minDiameter = diameter;
// 					}
// 					diameter = 0;
				}
				
			}
		}
		
		for (int i = 0; i < curpath; i++) {
			if (diams[i] > minDiameter) {
				minDiameter = diams[i];
			}
		}

		out.printf("%.6f",minDiameter);
		out.println();
		out.close();
	}
	
	private static void labelPath(int label, int start, double[][] matrix) {
		paths[start] = label;
		for (int i = 0; i < paths.length; i++) {
			if (matrix[start][i] > 0) {
				if (paths[i] == 0) {
					paths[i] = label;
					labelPath(label, i, matrix);
				}
			}
		}
	}
	
	private static int stillUnlabeled(int[] p) {
		for (int i = 0; i < p.length; i++) {
			if (p[i] == 0) {
				return i;
			}
		}
		return -1;
	}
}