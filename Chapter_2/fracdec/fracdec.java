/*
ID: cbnewma1
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {
	
	static int[] totalCowsFrom;
	static boolean[] visited;
	static boolean[][] adj;
	
 	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		int dem = Integer.parseInt(st.nextToken());
		
		StringBuilder res = new StringBuilder("");
		
		int q = 0;
		int d = -1;
		if (num >= dem) {
			//System.out.println("hi");
			res.append(num/dem); res.append(".");
			d = num%dem;
			if (d == 0) {
				//System.out.println("hello");
				res.append("0");
				out.println(res);
				out.close();
				return;
			}
			
			num = d;
			
		} else {
			res.append("0.");
		}
		
		//now we should only have decimals less than 1;
		
		//gets rid of more than on 
		
		int offset = res.length();
		//int numTens = 0;
		//Map<Integer, Integer> prevs = new HashMap<>();
		int[] memory= new int[dem]; //much better solution than hashmap but not bad...
		Arrays.fill(memory, -1);
		int i = 0;
		while (10*num < dem) {
			num*=10;
			res.append("0");
			memory[(int)Math.pow(10,i)] = i++;
			//prevs.put((int)Math.pow(10, i), i++);
		}
		
		
		
		//prevs.put(num, i++);
		memory[num] = i++;
		while (d != 0) {
			num *= 10;
			
			d = num%dem;
			// try {
// 				q = prevs.get(d);
// 			} catch (Exception e) {
// 				q = -1;
// 			}
			//System.out.println(d);
			res.append(num/dem);
			//if (q != -1) {
			if (memory[d] > -1) {
				res = new StringBuilder(res.substring(0, memory[d]+offset) + "(" + res.substring(memory[d]+offset, res.length()) + ")");
				break;
			}
			
			//prevs.put(d, i++);
			memory[d] = i++;
			num = d;
		}
		
		
		while (res.length() > 76) {
			out.println(res.substring(0, 76));
			res.delete(0, 76);
		}
		out.println(res);
		out.close();
		
		
	}
}