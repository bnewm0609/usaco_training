/*
ID: cbnewma1
TASK: maze1
LANG: JAVA
*/
import java.util.*;
import java.io.*;

public class maze1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		String[] maze = new String[2*h+1];
		for (int i = 0; i < 2*h+1; i++) {
			maze[i] = f.readLine();
		}
		
		int[] edgesR = new int[2*h+2*w];
		int[] edgesC = new int[2*h+2*w];
		int edgeCount = 0;
		
		int[][] distances = new int[h][w];
		for (int i = 0; i < distances.length; i++) {
			Arrays.fill(distances[i], Integer.MAX_VALUE);
		}
		//process maze: get rooms into 3d boolean[]
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		boolean[][][] rooms = new boolean[h][w][4];//0 is north, 1 is east, 2 is south, 3 is west
		for (int r = 1; r < 2*h+1; r+=2) { //rows in maze
			for (int c = 1; c < 2*w+1; c+=2) { //columns are rooms
				for (int i = 0; i < 4; i++) {
					if (maze[r+dr[i]].substring(c+dc[i], c+dc[i]+1).equals("-") || maze[r+dr[i]].substring(c+dc[i], c+dc[i]+1).equals("|")) {
						rooms[(r-1)/2][(c-1)/2][i] = false;
					} else {
						if(isEdge(r+dr[i], c+dc[i], h, w)) {
							rooms[(r-1)/2][(c-1)/2][i] = false;
							distances[(r-1)/2][(c-1)/2] = 1;
							edgesR[edgeCount] = (r-1)/2;
							edgesC[edgeCount] = (c-1)/2;
							edgeCount++;
						} else {
							rooms[(r-1)/2][(c-1)/2][i] = true;
						}
					}
				}
			}
		}
		
		// for (int i = 0; i < rooms.length; i++) {
// 			for (int j = 0; j < rooms[i].length; j++) {
// 				for (int k = 0; k < rooms[i][j].length; k++) {
// 					System.out.print(rooms[i][j][k] + " ");
// 				}
// 				System.out.print("\t");
// 			}
// 			System.out.println();
// 		}

		// for (int i = 0; i < distances.length; i++) {
// 			for (int j = 0; j < distances[0].length; j++) {
// 				System.out.print(distances[i][j] + "\t");
// 			}
// 			System.out.println();
// 		}
// 		
		for (int i = 0; i < edgeCount; i++) {
			startFill(rooms, distances, edgesR[i], edgesC[i]);
		}
		
		
		int max = 0;
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[0].length; j++) {
				//System.out.print(distances[i][j] + "\t");
				max = Math.max(max, distances[i][j]);
			}
			//System.out.println();
		}
		
		out.println(max);
		out.close();
		System.exit(0);
	}
	
	static void startFill(boolean[][][] rooms, int[][] distances, int startR, int startC) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for (int i = 0; i < 4; i++) {
			if (rooms[startR][startC][i]) {
				//System.out.println(startR+" " +startC);
				if (distances[startR+dr[i]][startC+dc[i]] > distances[startR][startC] + 1) {
					distances[startR+dr[i]][startC+dc[i]] = distances[startR][startC] + 1;
					startFill(rooms, distances, startR+dr[i], startC+dc[i]);
				}
			}
		}
	}
	
	static boolean isEdge(int r, int c, int rowLen, int colLen) {
		return r == 0 || r == rowLen*2 || c == 0 || c == colLen*2;
	}
}