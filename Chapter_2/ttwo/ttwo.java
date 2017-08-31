/*
ID: cbnewma
LANG: JAVA
TASK: ttwo
*/
import java.io.*;
import java.util.*;

public class ttwo {

	static boolean havePassed = false;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		
		
		boolean cowDone = false;
		boolean fjDone = false;
		boolean[][] traps = new boolean[10][10];
		boolean[][][] fjMoves = new boolean[10][10][4];
		//int[][][] fjDirs = new int[10][10];
		boolean[][][] cowMoves = new boolean[10][10][4];
		//int[][] cowDirs = new int[10][10];
		
		//Arrays.fill(fjDirs, -1);
		//Arrays.fill(cowDirs, -1);
		//boolean[][][][] fjMovesCount = new boolean[10][10][10][10];
		//boolean[][][][] cowMovesCount = new boolean[10][10][10][10];
		
		//int[][] fjMovesCount = new int[10][10];
		//int[][] cowMovesCount = new int[10][10];
		int fr = 0; int fc = 0; int cr = 0; int cc = 0;
		
		String row;
		for (int i = 0; i < 10; i++) {
			row = f.readLine();
			for (int j = 0; j < 10; j++) {
				if (row.substring(j, j+1).equals("*")) {
					traps[i][j] = true;
				} else if (row.substring(j, j+1).equals("F")) {
					fjMoves[i][j][0] = true;
					//fjDirs[i][j] = 0;
					fr = i; fc = j;
				} else if (row.substring(j, j+1).equals("C")){
					cr = i; cc = j;
					cowMoves[i][j][0] = true;
					//cowDirs[i][j] = 0;
				} 
			}
		}
		
		// for (int i = 0; i < 10; i++) {
// 				for (int j = 0; j < 10; j++) {
// 					if (i == fr && j == fc) {
// 						System.out.print("FJ\t");
// 					} else if (i == cc && j == cr){
// 						System.out.print("COW\t");
// 					} else if (traps[i][j]) {
// 						System.out.print("XXX\t");
// 					} else {
// 						System.out.print("000\t");
// 					}
// 				}
//  				System.out.println();
//  			}
//  			System.out.println("\n\n\n");
		// for (int i = 0; i < 10; i++) {
// 			for (int j = 0; j < 10; j++) {
// 				System.out.print(fjMoves[i][j]+"\t");
// 			}
// 			System.out.println();
// 		}
// 		System.out.println("\n\n\n");
		//0 is up, 1 is right, 2 is down, 3 is left
		int[] fjLoc = {fr, fc, 0, 0};
		int[] cowLoc = {cr, cc, 0, 0};
		while (fjLoc[0] != cowLoc[0] || fjLoc[1] != cowLoc[1]){
			if (fjLoc[0] == fr && fjLoc[1] == fc && cowLoc[0] == cr && cowLoc[1] == cc && fjLoc[3] > 0 && fjLoc[2] == 0 && cowLoc[2] == 0) {
				fjLoc[3] = 0;
				break;
			}
			//if (havePassed) {
			if (!havePassed(fjMoves, cowMoves) && fjDone && cowDone) {
				// System.out.println(havePassed(fjMoves, cowMoves) + " " + fjDone + " " + cowDone + " " + fjLoc[3]);
// 				for (int i = 0; i < 10; i++) {
// 				for (int j = 0; j < 10; j++) {
// 					if (i == fjLoc[0] && j == fjLoc[1]) {
// 						System.out.print("FJ\t");
// 					} else if (i == cowLoc[0] && j == cowLoc[1]){
// 						System.out.print("COW\t");
// 					} else if (traps[i][j]) {
// 						System.out.print("XXX\t");
// 					} else {
// 						System.out.print("000\t");
// 					}
// 				}
//  				System.out.println();
//  			}
//  			System.out.println("\n\n\n");
				fjLoc[3] = 0;
				break;
			}
			//}			
			fjLoc = genPaths(traps, fjLoc[0], fjLoc[1], fjLoc[2], fjLoc[3]);
			cowLoc = genPaths(traps, cowLoc[0], cowLoc[1], cowLoc[2], cowLoc[3]);
			
			if (fjMoves[fjLoc[0]][fjLoc[1]][fjLoc[2]] && fjLoc[3] > 0) {
				fjDone = true;
			}
			if (cowMoves[cowLoc[0]][cowLoc[1]][cowLoc[2]] && cowLoc[3] > 0) {
				cowDone = true;
			}
			fjMoves[fjLoc[0]][fjLoc[1]][fjLoc[2]] = true;
			cowMoves[cowLoc[0]][cowLoc[1]][cowLoc[2]] = true;
			// for (int i = 0; i < 10; i++) {
// 				for (int j = 0; j < 10; j++) {
// 					if (i == fjLoc[0] && j == fjLoc[1]) {
// 						System.out.print("FJ\t");
// 					} else if (i == cowLoc[0] && j == cowLoc[1]){
// 						System.out.print("COW\t");
// 					} else if (traps[i][j]) {
// 						System.out.print("XXX\t");
// 					} else {
// 						System.out.print("000\t");
// 					}
// 				}
//  				System.out.println();
//  			}
//  			System.out.println("\n\n\n");
			// for (int i = 0; i < 10; i++) {
// 				for (int j = 0; j < 10; j++) {
// 					if (i == fjLoc[0] && j == fjLoc[1]) {
// 						System.out.print("FJ\t");
// 					} else if (i == cowLoc[0] && j == cowLoc[1]){
// 						System.out.print("COW\t");
// 					} else if (fjMoves[i][j] == -1) {
// 						System.out.print("XXX\t");
// 					} else {
// 						System.out.print("000\t");
// 					}
// 				}
//  				System.out.println();
//  			}
//  			System.out.println("\n\n\n");
		}
		out.println(fjLoc[3]);
		out.close();
		System.exit(0);
	}
	
	static boolean havePassed(boolean[][][] m1, boolean[][][] m2) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				if (haveTrue(m1[i][j]) && haveTrue(m2[i][j])) {
					havePassed = true;
					return true;
				}
				
			}
		}
		return false;
	}
	
	static boolean haveTrue(boolean[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]) {
				return true;
			}
		}
		return false;
	}
	
	static int[] genPaths(boolean[][] moves, int startRow, int startCol, int dir, int time) {
		//int[] lastSpace = new int[2];
		int[][] dirsConv = {
			{-1, 0, 1, 0}, //xs
			{ 0, 1, 0, -1}//ys
		};
		//System.out.println("Hello There");
		int destRow = startRow+dirsConv[0][dir];
		int destCol = startCol+dirsConv[1][dir];
		//ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		if (destRow > 9 || destRow < 0 || destCol > 9 || destCol < 0 || moves[destRow][destCol]) {
			//genPaths(moves, startRow, startCol, (dir+1)%4, toAdd+1);
			return new int[]{startRow, startCol, (dir+1)%4, time+1};
		}
		
		
		//moves[destRow][destCol] = moves[startRow][startCol]+1;
		//genPaths(moves, destRow, destCol, dir, 1);
		return new int[]{destRow, destCol, dir, time+1};
		
		
	}
	
}