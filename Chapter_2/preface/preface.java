/*
ID: cbnewma1
LANG: JAVA
TASK: preface
*/
import java.util.*;
import java.io.*;

public class preface {

	static int[] ones;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int n = Integer.parseInt(f.readLine());
		int[] is = {0, 1, 3, 6, 7};
		int[] xs = new int[50]; //1 to 50 --- [9, 50) etc.
		int[] cs = new int[500]; //1 to 500
		
		// int[] vs = new int[6]; // 4 to 10 
// 		int[] ls = new int[50];  //50 to 91
// 		int[] ds = new int[500]; //400 to 901
		
		ones = new int[]{0,1,2,3,1};
		//boolean[] includes = new boolean[7];
		int m = 0;
		String res = "";
		
		if (n >= 900) {
			int newn = n - 899;
			if (newn > 2000) {
				m += 3*(newn - 2000);
				newn = 2000;
			}
			if (newn > 1000) {
				m += 2*(newn-1000);
				newn = 1000;
			}
			
			m += newn;
			res = "M " + m +"\n";
		} 
		if (n >= 400 ) {
			count5(500);
			m = round(n/1000.0) * count5(500);
			if (n%1000 >= 400 && n%1000 < 900)
				m += n%1000 - 399;
			res = "D " + m + "\n" + res;
		} 
		if (n >= 90) {
			m = count1(100, cs, n);
			m = n / 500 * m + cs[n%500];
			res = "C " + m + "\n" + res;
		}
		if (n >= 40) {
			m = round(n/100.0) * count5(50);
			if (n%100 >= 40 && n%100 < 90)
				m += n%100 - 39;
			res = "L " + m + "\n" + res;
		}
		if (n >= 9) {
			m = count1(10, xs, n);
			//System.out.println(m);
			m = n / 50 * m + xs[n%50];
			//System.out.println(xs[20]);
			res = "X " + m + "\n" + res;
		}
		if (n >= 4) {
			m = round(n/ 10.0) * count5(5);
			System.out.println(round(n/10.0));
			if (n%10 >= 4 && n%10 < 9)
				m += n%10 - 3;
			// if (n%10 == 9)
// 				m+=
			//m = n / 10 * m + Math.max(n % 10 - 4, 0);
			res = "V " + m + "\n" + res;
		}
		m = 7;
		//System.out.println(m);
		m = n/5 * m + is[n%5];
		//System.out.println(is[1]);
		res = "I " + m + "\n" + res;
		
		out.print(res);
		out.close();
		System.exit(0);
		
	}
	static int round(double x) {
		//rounds up if greater than or equal to *.9 otherwise rounds down
		if (x - (int)x - 0.9 >= -0.0001) {
			return (int)Math.round(x);
		} else {
			//System.out.println(x - (int)x);
			return (int)Math.floor(x);
		}
	}
	
	static int count1(int a, int[] as, int n) {
		//int start = a - a/10;
		int an = a/10;
		//System.out.println(start);
		int sum = 0;
		for (int i = 0; i < 5*a; i++) {
			
			if (i < 19*an && i >= 9*an || i < 49*an && i >=40*an) {
				sum += 1;
				as[i] = sum;
			} else if (i < 29*an && i >= 19*an || i >= 49*an && i < 50*an){
				sum += 2;
				as[i] = sum;
			} else if ( i < 39*an && i >= 29*an){
				sum += 3;
				as[i] = sum;
			} else if (i >= 39*an && i < 40*an) {
				sum += 4;
				as[i] = sum;
			}
						
			//System.out.println(sum);

		}
		return sum;
	}
	
	static int count5(int b) {
		int start = b - b/5;
		
		return 2*b - b/5 - start;
	}
}