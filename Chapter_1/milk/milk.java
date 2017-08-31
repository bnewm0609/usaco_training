/*
ID: cbnewma1
LANG: JAVA
PROG: milk
*/
import java.util.*;
import java.io.*;

class milk {
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numUnits = Integer.parseInt(st.nextToken());
		int numLines = Integer.parseInt(st.nextToken());
		int ppu;
		int numFarmUnits;
		ArrayList<Farmer> suppliers = new ArrayList<Farmer>();
		
		for (int i = 0; i <  numLines; i++) {
			st = new StringTokenizer(f.readLine());
			ppu = Integer.parseInt(st.nextToken());
			numFarmUnits = Integer.parseInt(st.nextToken());
			suppliers.add(new Farmer(ppu, numFarmUnits));
		}
		
		//setup complete
		int lowestPrice = getLowestPrice(numUnits, suppliers);
		out.println(lowestPrice);
		//System.out.println(lowestPrice);
		out.close();
		System.exit(0);

	}	
	
	
	static int getLowestPrice(int numUnits, ArrayList<Farmer> suppliers) {
		Farmer lowestSup = null;
		int lowestPrice = 1001; //max is 1000
		int lowestIndex = -1;
		for (int i = 0; i < suppliers.size(); i++) {
			if (suppliers.get(i).getPPU() < lowestPrice) {
				lowestSup = suppliers.get(i);
				lowestPrice = lowestSup.getPPU();
				lowestIndex = i;
			}
		}
		try {
			if (lowestSup.getNumUnits() < numUnits) {
				suppliers.remove(lowestIndex);
				int neededUnits = numUnits - lowestSup.getNumUnits();
				return (lowestSup.getNumUnits() * lowestSup.getPPU()) + getLowestPrice(neededUnits, suppliers);
			} else {
				return numUnits * lowestSup.getPPU();
			}
		
		} catch (NullPointerException ex) {
			//lowestSup wasn't initiallized
			return 0;
		}
	}
}

class Farmer {
	
	private int ppu;
	private int numUnits;
	
	public Farmer(int pricePerUnit, int numberOfUnits) {
		ppu = pricePerUnit;
		numUnits = numberOfUnits;
	}
	
	public int getPPU() {
		return ppu;
	}
	
	public int getNumUnits() {
		return numUnits;
	}
	
}
