/*
ID: cbnewma1
LANG: JAVA
PROG: barn1
*/
import java.util.*;
import java.io.*;
class barn1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int m = Integer.parseInt(st.nextToken()); //max # of boards
		int s = Integer.parseInt(st.nextToken()); //total # of stalls
		int c = Integer.parseInt(st.nextToken()); //# of occupied stalls
		int[] ocStalls = new int[c];
		for (int i = 0; i < c; i++) {
			ocStalls[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(ocStalls);
		Board startBoard = new Board(0, c-1, ocStalls[ocStalls.length-1] - ocStalls[0]);
		Board[] covers = new Board[Math.min(m, c)];
		covers[0] = startBoard;
		int minCovered = minStalls(covers, Math.min(m, c), 0, ocStalls);
		//System.out.println(minCovered);
		out.println(minCovered);
		out.close();
		System.exit(0);
	}
	
	
	static int minStalls(Board[] stallCovers, int maxBoards, int currIndex, int[] occStalls) {
		if (currIndex == maxBoards-1){
			return sumOfLengths(stallCovers);
		}
		if (occStalls.length == currIndex) {
			return sumOfLengths(stallCovers);
		}
		
		Gap[] gaps = new Gap[currIndex+1];
		for (int i = 0; i <= currIndex; i++) {
			gaps[i] = getMaxGap(stallCovers[i], i, occStalls);
		}
		Gap maxGap = maximize(gaps);
		
		int startIndex = maxGap.board.startIndex;
		int endIndex = maxGap.startIndex;
		//System.out.println("currIndex: " + currIndex + "\tstartIndex: " + startIndex + "\tendIndex: " + endIndex);
		Board b1 = new Board(startIndex, endIndex, occStalls[endIndex] - occStalls[startIndex]);
		startIndex = maxGap.startIndex + 1;
		endIndex = maxGap.board.endIndex;
		//System.out.println("currIndex: " + currIndex + "\tstartIndex: " + startIndex + "\tendIndex: " + endIndex);
		Board b2 = new Board(startIndex, endIndex, occStalls[endIndex] - occStalls[startIndex]);
		
		stallCovers[maxGap.boardIndex] = b1;
		//System.out.println(currIndex + 1);
		stallCovers[++currIndex] = b2;
		
		return minStalls(stallCovers, maxBoards, currIndex, occStalls);
	}
	
	static int sumOfLengths(Board[] boards) {
		int total = 0;
		for (int i = 0; i < boards.length; i++) {
			//System.out.println(boards[i].length);
			if (boards[i] != null){
				total += boards[i].length + 1; //this looks so confusing!!
			}
		}
		return total;
	}
	
	static Gap getMaxGap(Board b, int boardIndex, int[] occStalls) {
		int maxHoleLen = 0;
		int maxHoleSI = 0;
		//int maxHoleEI = 0;
		
		int currHoleLen = 0;
		//int currHoleSI = 0;
		for (int i = b.startIndex + 1; i <= b.endIndex; i++) {
			currHoleLen = occStalls[i] - occStalls[i-1];
			if(currHoleLen > maxHoleLen) {
				maxHoleLen = currHoleLen;
				maxHoleSI = i-1;
			}
		}
		
		return new Gap(maxHoleLen, maxHoleSI, b, boardIndex);
	}
	
	static Gap maximize(Gap[] gaps) {
		int maxLen = gaps[0].length;
		Gap maxGap = gaps[0];
		for (int i = 0; i < gaps.length; i++) {
			if (gaps[i].length > maxLen) {
				maxLen = gaps[i].length;
				maxGap = gaps[i];
			}
		}
		return maxGap;
	}
}

class Gap{
	int length;
	Board board;
	int boardIndex;
	int startIndex; //inclusive, for splitting the array
	//int endIndex; //not inclusive (i.e. there will be a cow in this spot
	
	public Gap(int len, int si, Board b, int bi) {
		length = len;
		startIndex = si;
		board = b;
		boardIndex = bi;
	}
}

class Board {
	int startIndex; //inclusive
	int endIndex; //inclusive
	int length;
	
	public Board(int si, int ei, int len) {
		startIndex = si;
		endIndex = ei;
		length = len;
	}
}