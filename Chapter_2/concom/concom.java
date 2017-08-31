/*
ID: cbnewma1
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom {

	static boolean[][] haveChecked;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		int numCompanies = Integer.parseInt(f.readLine());
		
		
		
		int[] names = new int[101];
		int[] numbers = new int[101];
		Arrays.fill(names, -1);
		int counter = 0;
		int[][] ownershipp = new int[101][101];
		
		StringTokenizer st;
		for (int i = 0; i < numCompanies; i++) {
			st = new StringTokenizer(f.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			if (names[c1] == -1) {
				numbers[counter] = c1;
				names[c1] = counter++;
			}
			int c2 = Integer.parseInt(st.nextToken());
			if (names[c2] == -1) {
				numbers[counter] = c2;
				names[c2] = counter++;
			}
			ownershipp[names[c1]][names[c2]] = Integer.parseInt(st.nextToken());
			
		}
		
		haveChecked = new boolean[counter][counter];
		int[][] ownership =  new int[counter][counter];
		for (int r = 0; r < counter; r++) {
			for (int c = 0; c < counter; c++) {
				ownership[r][c] = ownershipp[r][c];
			}
		}
			
		//printOwnership(ownership);
		for (int i = 0; i < counter-1; i++) {
			updateOwnership(ownership, i);
		}
		
		System.out.println();
		
		ArrayList<Dumdum> output = new ArrayList<Dumdum>();
		for (int r = 0; r < ownership.length; r++) {
			for (int c = 0; c < ownership[r].length; c++){
				if (ownership[r][c] > 50 && r != c){
					//output[cont] = (new Dumdum(numbers[r], numbers[c]));
					output.add(new Dumdum(numbers[r], numbers[c]));
					//cont++;
				}
			}
		}
		
		
		Collections.sort(output, new DumDumComparator());
		for (Dumdum s: output) {
			out.println(s);
		}
		
		out.close();
		
		//printOwnership(ownership);
		
		System.exit(0);
	}
	
	
	static void updateOwnership(int[][] ownership, int owner) {
		for (int i = 0; i < ownership[owner].length; i++) {
			if (ownership[owner][i] > 50 && !haveChecked[owner][i] && owner != i) {
				haveChecked[owner][i] = true;
				updateOwnership(ownership, i);
				combineRows(ownership, owner, i);
				i = 0;
			}
		}
	}
	
	static void combineRows(int[][] matrix, int destRow, int srcRow) {
		for (int i = 0; i < matrix[0].length; i++) {
			matrix[destRow][i] = Math.min(matrix[destRow][i] + matrix[srcRow][i], 100);
		}
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[srcRow][j] < 50) {
				matrix[srcRow][j] = 0;
			}
		}
		//matrix[srcRow] = new int[matrix[srcRow].length];
	}
	
	static void printOwnership(int[][] ownership) {
		for (int r = 0; r < ownership.length; r++) {
			for (int c = 0; c < ownership[r].length; c++){
				System.out.print(ownership[r][c] + " ");
			}
			System.out.println();
		}
	}
}


class Dumdum {
	
	int one;
	int two;
	
	public Dumdum(int i1, int i2) {
		one  = i1;
		two = i2;
	}
	
	public String toString() {
		return one +" " +two;
	}
	
	
	public boolean equals(Dumdum other) {
		return compareTo(other) == 0;
	}
	
	public int compareTo(Dumdum other) {
		if (one == other.one && two == other.two) {
		
			return 0;
		
		} else if (one == other.one) {
			return two > other.two ? 1:-1;
		} else {
			return one > other.one? 1:-1;
		}
	}
}

class DumDumComparator implements Comparator<Dumdum> {
    @Override
    public int compare(Dumdum o1, Dumdum o2) {
        if (o1.one == o2.one && o1.two == o2.two) {
			return 0;
		} else if (o1.one == o2.one) {
			return o1.two > o2.two ? 1:-1;
		} else {
			return o1.one > o2.one? 1:-1;
		}
    }
}
