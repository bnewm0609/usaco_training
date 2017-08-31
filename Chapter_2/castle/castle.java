/*
ID: cbnewma1
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

public class castle {

	static int[][] castleRaw;
	static int[][] castleComponents;
	static int[] decode = {8,4,2,1};
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};
	static int compCount = 0;
	static ArrayList<Integer> compLengthList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int len = Integer.parseInt(st.nextToken());
		int wid = Integer.parseInt(st.nextToken());
		
		//loading raw nums
		castleRaw = new int[wid][len];
		for(int row = 0; row < wid; row++) {
			st = new StringTokenizer(f.readLine());
			for (int col = 0; col < len; col++) {
				castleRaw[row][col] = Integer.parseInt(st.nextToken());
				//System.out.print(castleRaw[row][col] + " ");
			}
			//System.out.println();
		}
		
		compLengthList = new ArrayList<Integer>();
		castleComponents = new int[wid][len]; //0 means that the room hasn't been visited
		int compNum = 1; //id for the component
		int maxCompCount = 0;
		for (int row = 0; row < wid; row++) {
			for (int col = 0; col < len; col++) {
				if(castleComponents[row][col] == 0) {
					//System.out.println();
					findComponent(row, col, compNum);
					compNum++;
					compLengthList.add(compCount); //index is one less than component #
					if (compCount > maxCompCount) maxCompCount = compCount;
					compCount = 0;
				}
			}
		}
		out.println(compLengthList.size());
		out.println(maxCompCount);
		
		boolean[][] checked = new boolean[compLengthList.size()][compLengthList.size()];
		for (int i = 0; i < checked.length; i++) {
			checked[i][i] = true;
		}
		
		int maxSum = 0;
		int tempSum = 0;
		int doubleMax = 2*maxCompCount;
		for(int i = 0; i < compLengthList.size(); i++) {
			tempSum = maxCompCount+compLengthList.get(i);
			if ( tempSum > maxSum && tempSum != doubleMax) {
				maxSum = tempSum;
			}
		}
		
		
		int currComponent = 0;
		int nComp = 0;
		int nIndex = 0;
		int maxTotal = 0;
		int tempTotal = 0;
		
		int maxRoomRow = 0;
		int maxRoomCol = 0;
		char maxRoomDir = 'N';
		for (int col = 0; col < castleComponents[0].length; col++) { // west to est
			for (int row = castleComponents.length - 1; row >= 0; row--) { // south to north
				currComponent = castleComponents[row][col];
				for (int neighbor = 2; neighbor > 0; neighbor--) { // So we check North before East
					
					if (col+dCol[neighbor] < 0 || col+dCol[neighbor] >= castleComponents[0].length ||
						 row + dRow[neighbor] < 0 || row + dRow[neighbor] >= castleComponents.length) {
						 	continue;
					}
					
					nComp = castleComponents[row+dRow[neighbor]][col+dCol[neighbor]];
					if (checked[nComp-1][currComponent-1]) {
						continue;
					}
					
					checked[nComp-1][currComponent-1] = true;
					tempTotal = compLengthList.get(nComp-1) + compLengthList.get(currComponent-1);
					if (tempTotal > maxTotal) {
						maxTotal = tempTotal;
						maxRoomRow = row+1;
						maxRoomCol = col+1;
						if (neighbor == 1) maxRoomDir = 'E';
						else maxRoomDir = 'N';
					}
				}
			}
		}
		out.println(maxTotal);
		out.println(maxRoomRow + " " + maxRoomCol + " " + maxRoomDir);
		out.close();
		System.exit(0);
	}
	
	static void findComponent(int row, int col, int compNum) {
		castleComponents[row][col] = compNum;
		compCount++;
		int num = castleRaw[row][col];
		//System.out.println("num initial: " + num);
		for (int i = 0; i < 4; i++) {
			if (num - decode[i] >= 0) {
				num -= decode[i];
				//System.out.println("\tnum: " + num);
			} else {
				int newRow = row + dRow[i];
				int newCol = col + dCol[i];
				//System.out.println("("+newRow + " " + newCol + ")");
				
				if (castleComponents[newRow][newCol] == 0) {
					findComponent(newRow, newCol, compNum);
				}
			}
		}
	}
}