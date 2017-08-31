/*
ID: cbnewma1
LANG: JAVA
PROG: transform
*/
import java.util.*;
import java.io.*;

class transform {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		char[] pieces;
		int length = Integer.parseInt(f.readLine());
		char[][] b1 = new char[length][length];
		for (int i = 0; i < length; i++) {
			pieces = f.readLine().toCharArray();
			for (int j = 0; j < length; j++) {
				b1[i][j] = pieces[j];
			}
		}
		
		char[][] b2 = new char[length][length];
		for (int i = 0; i < length; i++) {
			pieces = f.readLine().toCharArray();
			for (int j = 0; j < length; j++) {
				b2[i][j] = pieces[j];

			}
		}
		int answer = 7;
		if (r90(b1, b2, length)) {
			answer = 1;
		} else if (r180(b1, b2, length)) {
			answer = 2;
		} else if (r270(b1, b2, length)) {
			answer = 3;
		} else if (reflect(b1, b2, length)){
			answer = 4;
		} else if (same(b1, b2, length)) {
			answer = 6;
		} else if (combo(b1, b2, length)) {
			answer = 5;
		}
		
		System.out.println(answer);
		out.println(answer);
		out.close();
		System.exit(0);
	}
	
	
	static boolean r90(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				if (!(b2[i][j] == b1[n-j][i])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean r180(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				if (!(b2[i][j] == b1[n-i][n-j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean r270(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				if (!(b2[i][j] == b1[j][n-i])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean reflect(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				if (!(b2[i][j] == b1[i][n-j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean same(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				if (!(b2[i][j] == b1[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean combo(char[][] b1, char[][] b2, int len) {
		int n = len - 1;
		char[][] temp = new char[len][len];
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				temp[i][j] = b1[i][n-j];
			}
		}
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < b1.length; j++) {
				b1[i][j] = temp[i][j];
			}
		}
		return (r90(b1, b2, len) || r180(b1, b2, len) || r270(b1, b2, len));
	}
	
}

