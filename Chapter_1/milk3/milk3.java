/*
ID: cbnewma1
LANG: JAVA
TASK: milk3
*/
import java.util.*;
import java.io.*;
class milk3 {

	static boolean[][][] seenNums;
	static int[] usedCs;
	static int cCount = 0;
	
	
	static int iter = 0;
	static int[] maxes;
	static int[] cups;
	
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		maxes = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		cups = new int[]{0, 0, maxes[2]};
		seenNums = new boolean[maxes[0] + 1][maxes[1] + 1][maxes[2] + 1];
		usedCs = new int[maxes[2]];
		
		//System.out.println("a->b\n");
		mothersMilk(0, 1);
		cups = new int[] {0, 0, maxes[2]};
		//System.out.println("c->a\n");
		mothersMilk(2, 0);
		cups = new int[] {0, 0, maxes[2]};
		//System.out.println("c->b\n");
		mothersMilk(2, 1);
		
		int[] finalCs = new int[cCount];
		//System.out.println("-------------------------------------------------");
		for (int i = 0; i < finalCs.length; i++) {
			//System.out.println(i + ": " + usedCs[i]);
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
	
	public static void mothersMilk(int src, int dest) {
		iter++;
		int origSrc = cups[src];
		int origDest = cups[dest];
		pour(src, dest);
		//System.out.println(iter + ": " + cups[0] + " " + cups[1] + " " + cups[2]+"\tsrc: "+src + "  dest: " + dest);

		if (seenNums[cups[0]][cups[1]][cups[2]]) {
			//pour(dest, src);
			cups[src] = origSrc;
			cups[dest] = origDest;
			//System.out.println("\tUsed Number");
			return;
		}
		seenNums[cups[0]][cups[1]][cups[2]] = true;
		if (cups[0] == 0) {
			if (!isIn(cups[2], usedCs, cCount)) {
				usedCs[cCount++] = cups[2];
			}
			//cups[src] = origSrc;
			//cups[dest] = origDest;
			//System.out.println("\tAdded to usedCs");
			//return;
		}
		
		if (src == 0 || dest == 0) {
			mothersMilk(1, 2);
			mothersMilk(2, 1);
		}
		if (src == 1 || dest == 1) {
			mothersMilk(0, 2);
			mothersMilk(2, 0);
		}
		if (src == 2 || dest == 2) {
			mothersMilk(0, 1);
			mothersMilk(1, 0);
		}
		cups[src] = origSrc;
		cups[dest] = origDest;
	}
	
	public static void pour(int src, int dest) {
		int amount = Math.min(cups[src], maxes[dest] - cups[dest]);
		cups[src] -= amount;
		cups[dest] += amount;
	}
	
	public static boolean isIn(int num, int[] nums, int len) {
		for (int i = 0; i < len; i++) {
			if (num == nums[i]) {
				return true;
			}
		}
		return false;
	}
	
}