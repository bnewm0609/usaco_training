/*
ID: cbnewma1
LANG: JAVA
TASK: frac1
*/
import java.util.*;
import java.io.*;

public class frac1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int N = Integer.parseInt(f.readLine());
		ArrayList<Double> results = new ArrayList<Double>();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		ArrayList<Integer> dems = new ArrayList<Integer>();
		
		results.add(0.0); results.add(1.0);
		nums.add(0); nums.add(1);
		dems.add(1); dems.add(1);
		
		double res = 0;
		for (int d = 2; d <= N; d++) { // looking through denominators
			for (int n = 1; n < d; n++) { // looking through numerators
				//System.out.println("num: " + n + "\tdem: " + d + "\tgcf: " + gcf(n,d));
				if (gcf(n, d) > 1) {
					continue;
				}
				
				res = ((double)n)/d;
				//if (res <= 0.5) {
					//System.out.println("less than 1/2");
					//System.out.println(n+ "/" + d);
					for (int r = 0; r <= results.size(); r++) {
						if (results.get(r) > res) {
							results.add(r, res);
							nums.add(r, n);
							dems.add(r, d);
							break;
						}
					}
				//} else {
					//System.out.println("greater than 1/2");
					//System.out.println(n+ "/" + d);
					// for (int r = results.size() - 1; r >= results.size()/2; r--) {
// 						if (results.get(r) < res) {
// 							results.add(r+1, res);
// 							nums.add(r+1, n);
// 							dems.add(r+1, d);
// 							//System.out.println(n+ "/" + d);
// 							break;
// 						}
// 					}
// 				}
			}
		}
		//System.out.println(dems);
		for (int i = 0; i < results.size(); i++) {
			out.println(nums.get(i)+"/"+dems.get(i));
		}
		out.close();
		System.exit(0);
	}
	
	static int gcf(int a, int b) {
		if (a == 0) {
			return b;
		}
		
		return gcf(b%a, a);
	}
}