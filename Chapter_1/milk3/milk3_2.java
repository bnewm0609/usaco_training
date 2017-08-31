/*
ID: cbnewma1
LANG: JAVA
TASK: milk3
*/
import java.util.*;
import java.io.*;

class milk3 {

	static int cCount = 0;
	static int[] usedCs;
	static int numUsedNums = 0;
	static String[] usedNums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] maxes = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		int[] cups = {0, 0, maxes[2]};
		usedNums = new String[(int) Math.pow(maxes[2]/2 +1, 2)];
		usedCs = new int[maxes[2]];
		
		//int[] practiceCont = {1,2,3,4};
		//System.out.println(contains(4, practiceCont, practiceCont.length));
		System.out.println("\nA --> B:");
		pour(0, 1, cups, maxes);
		System.out.println("\nB --> A:");
		cups = new int[]{0, 0, maxes[2]};
		pour(1, 0, cups, maxes);
		System.out.println("\nA --> C:");
		cups = new int[]{0, 0, maxes[2]};
		pour(0, 2, cups, maxes);
		System.out.println("\nC --> A:");
		cups = new int[]{0, 0, maxes[2]};
		pour(2, 0, cups, maxes);
		System.out.println("\nB --> C:");
		cups = new int[]{0, 0, maxes[2]};
		pour(1, 2, cups, maxes);
		System.out.println("\nC --> B:");
		cups = new int[]{0, 0, maxes[2]};
		pour(2, 1, cups, maxes);
		
		int[] finalCs = new int[cCount];
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < finalCs.length; i++) {
			System.out.println(i + ": " + usedCs[i]);
			finalCs[i] = usedCs[i];
		}
		Arrays.sort(finalCs);
		for (int j = 0; j < finalCs.length - 1; j++) {
			out.print(finalCs[j] + " ");
		}
		out.println(finalCs[finalCs.length - 1]);
		out.close();
		System.exit(0);
		
	}
	
	public static void pour(int src, int dest, int[] cups, int[] maxes) {
		int amountPoured = Math.min(cups[src], maxes[dest] - cups[dest]);
		//System.out.println(amountPoured + " from " + cups[src] + " to " + cups[dest]);
		if (src == 2 && dest == 0) {
			System.out.println("C-->A Before: " + cups[0] + " " + cups[1] + "  " + cups[2]);
		}
		
		cups[src] -= amountPoured;
		int x = cups[src];
		cups[dest] += amountPoured;
		int y = cups[dest];
		
		//Debugging
		// if (src == 2 && dest == 0) {
// 			System.out.println("C-->A Before: " + cups[0] + " " + cups[1] + "  " + cups[2]);
// 		}
		
		
		//creates unique base-21 number for id
		//int myNum = 21*21*cups[0] + 21*cups[1] + cups[2];
		String myNum = cups[0] + " " + cups[1] + " " + cups[2];
		if (contains(myNum, usedNums, numUsedNums)) {
			cups[src] += amountPoured;
			cups[dest] -= amountPoured;
			return;
		}
		//System.out.println(usedNums);
		if (numUsedNums >= usedNums.length) return;
		usedNums[numUsedNums] = myNum;
		numUsedNums++;
		if (cups[0] == 0) { //A is 0
			if (!contains(cups[2], usedCs, cCount)) { //I haven't already recorded C
				System.out.println(cups[0] + " " + cups[1] + "  " + cups[2]);
				usedCs[cCount] = cups[2];
				cCount++;
				System.out.print("USED Cs\n\t");
				for (int i: usedCs) { System.out.print(i + " "); } 
				System.out.println();
			}
			cups[src] += amountPoured;
			cups[dest] -= amountPoured;
			return;
		}
		
		if (src == 0 || dest == 0) {
			pour(1, 2, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
			pour(2, 1, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
		}
		
		if (src == 1 || dest == 1) {
			pour(0, 2, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
			pour(2, 0, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
		}
		
		if (src == 2 || dest == 2) {
			pour(0, 1, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
			pour(1, 0, cups, maxes);
			cups[src] = x;
			cups[dest] = y;
		}
		
		
		
		
	}
	
	public static boolean contains(int num, int[] toSearch, int numElems) {
		for (int i = 0; i < numElems; i++) {
			if (toSearch[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(String num, String[] toSearch, int numElems) {
		for (int i = 0; i < numElems; i++) {
			if (toSearch[i].equals(num)) {
				return true;
			}
		}
		return false;
	}
}

/*
ID: cbnewma1
LANG: JAVA
TASK: milk3
*/
import java.util.*;
import java.io.*;

class milk3 {

	static int cCount = 0;
	static int[] usedCs;
	static int numUsedNums = 0;
	static int[] usedNums;
	static int[] maxes;
	static boolean f = false;
	static boolean t = true;
	
	public static void main(String[] args) throws IOException {
		BufferedReader fl = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(fl.readLine());
		
		maxes = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		int[] cups = {0, 0, maxes[2]};
		usedNums = new int[(int) Math.pow(maxes[2]/2 +1, 2)];
		usedCs = new int[maxes[2]];
		
		//int[] practiceCont = {1,2,3,4};
		//System.out.println(contains(4, practiceCont, practiceCont.length));
		
		
		System.out.println("\nA --> B:");
		pour(0, 0, maxes[1], 0, 0, maxes[2], 0);
		System.out.println("\nC --> A:");
		pour(maxes[2], 0, maxes[0], 0, 0, maxes[2], 3);
		System.out.println("\nC --> B:");
		pour(maxes[2], 0, maxes[1], 0, 0, maxes[2], 5);
		
		int[] finalCs = new int[cCount];
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < finalCs.length; i++) {
			System.out.println(i + ": " + usedCs[i]);
			finalCs[i] = usedCs[i];
		}
		Arrays.sort(finalCs);
		for (int j = 0; j < finalCs.length - 1; j++) {
			out.print(finalCs[j] + " ");
		}
		out.println(finalCs[finalCs.length - 1]);
		out.close();
		System.exit(0);
		
	}
	//			   a-b|b-a|a-c|c-a|b-c|c-b
	//assignment = {F,  F,  F,  F,  F,  T}
	 
	public static void pour(int src, int dest, int maxDest, int A, int B, int C, int dir) {
		System.out.println(A + "  " + B + " " + C);
		
		int amountPoured = Math.min(src, maxDest - dest);
				
		//creates unique base-21 number for id
		int myNum = 21*21*A + 21*B + C;
		if (contains(myNum, usedNums, numUsedNums)) {
			return;
		}
		
		//System.out.println(usedNums);
		//if (numUsedNums >= usedNums.length) return;
		usedNums[numUsedNums] = myNum;
		numUsedNums++;
		
		
		
		if (A == 0) { //A is 0
			
			
			if (!contains(C, usedCs, cCount)) { //I haven't already recorded C
				//System.out.println(cups[0] + " " + cups[1] + "  " + cups[2]);
				
				//if (cCount >= usedCs.length) return;
				usedCs[cCount] = C;
				cCount++;
				
				System.out.print("USED Cs\n\t");
				for (int i: usedCs) { System.out.print(i + " "); } 
				System.out.println();
			}
			return;
		}
		
		System.out.println("Hello");
		
		
		if (dir == 0) {
			//dest order: A->B, B->A, A->C, C->A, B->C, C->B
			
			//A->C
			pour(A-amountPoured, C, maxes[2], A-amountPoured, B+amountPoured, C, 2);
			//C->A
			pour(C, A-amountPoured, maxes[0], A-amountPoured, B+amountPoured, C, 3);
			//B->C
			pour(B+amountPoured, C, maxes[2], A-amountPoured, B+amountPoured, C, 4);
			//C->B
			pour(C, B+amountPoured, maxes[1], A-amountPoured, B+amountPoured, C, 5);
		} else if (dir == 1) {
			//A->C
			pour(A+amountPoured, C, maxes[2], A+amountPoured, B-amountPoured, C, 2);
			//C->A
			pour(C, A+amountPoured, maxes[0], A+amountPoured, B-amountPoured, C, 3);
			//B->C
			pour(B-amountPoured, C, maxes[2], A+amountPoured, B-amountPoured, C, 4);
			//C->B
			pour(C, B-amountPoured, maxes[1], A+amountPoured, B-amountPoured, C, 5);
		} else if (dir == 2) {
			//dest order: A->B, B->A, A->C, C->A, B->C, C->B
				
			//A->B
			pour(A-amountPoured, B, maxes[1], A-amountPoured, B, C+amountPoured, 0);
			//B->A
			pour(B, A-amountPoured, maxes[0], A-amountPoured, B, C+amountPoured, 1);
			//B->C
			pour(B, C+amountPoured, maxes[2], A-amountPoured, B, C+amountPoured, 4);
			//C->B
			pour(C+amountPoured, B, maxes[1], A-amountPoured, B, C+amountPoured, 5);
		} else if (dir == 3) {
			System.out.println("c->a");
			//A->B
			pour(A+amountPoured, B, maxes[1], A+amountPoured, B, C-amountPoured, 0);
			//B->A
			pour(B, A+amountPoured, maxes[0], A+amountPoured, B, C-amountPoured, 1);
			//B->C
			pour(B, C-amountPoured, maxes[2], A+amountPoured, B, C-amountPoured, 4);
			//C->B
			pour(C-amountPoured, B, maxes[1], A+amountPoured, B, C-amountPoured, 5);
		} else if (dir == 4) {
			//dest order: A->B, B->A, A->C, C->A, B->C, C->B
			
			//A->B
			pour(A, B-amountPoured, maxes[1], A, B-amountPoured, C+amountPoured, 0);
			//B->A
			pour(B-amountPoured, A, maxes[0], A, B-amountPoured, C+amountPoured, 1);
			//A->C
			pour(A, C+amountPoured, maxes[2], A, B-amountPoured, C+amountPoured, 2);
			//C->A
			pour(C+amountPoured, A, maxes[0], A, B-amountPoured, C+amountPoured, 3);
			
		} else if (dir == 5) {
			//A->B
			pour(A, B+amountPoured, maxes[1], A, B+amountPoured, C-amountPoured, 0);
			//B->A
			pour(B+amountPoured, A, maxes[0], A, B+amountPoured, C-amountPoured, 1);
			//A->C
			pour(A, C-amountPoured, maxes[2], A, B+amountPoured, C-amountPoured, 2);
			//C->A
			pour(C-amountPoured, A, maxes[0], A, B+amountPoured, C-amountPoured, 3);
		}		
		

	}
	
	public static boolean contains(int num, int[] toSearch, int numElems) {
		for (int i = 0; i < numElems; i++) {
			if (toSearch[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public static int indexOf(boolean[] haystack, boolean needle) {
		for (int i = 0; i <  haystack.length; i++) {
			if (haystack[i] == needle) {
				return i;
			}
		}
		return -1;
	}
}