/*
ID: cbnewma1
LANG: JAVA
PROG: friday
*/
import java.util.*;
import java.io.*;
class friday {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		
		int n = Integer.parseInt(f.readLine());
		int end_year = 1899 + n;
		//1 sunday, 2 mon, 3 tues, 4 wed, 5 thurs, 6 fri, 0 sat
		int[] startThirteens = {0,3,3,6,1,4,6,2,5,0,3,5};
		int[] thirteens = new int[7];
		
		for (int k = 0; k < 12; k++){
			thirteens[startThirteens[k]]++;
		}
		
		for (int j = 1901; j < 1900+n; j++){
			//current year: leap year
			System.out.println("Curr. year: " + (j));
			if (j % 100 == 0) {
				if (j % 400 == 0){
					//System.out.println("\t1.1");
					for (int i = 0; i < startThirteens.length; i++){
						if (i < 2){
							startThirteens[i] = (startThirteens[i] + 1) % 7;
						} else {
							startThirteens[i] = (startThirteens[i] + 2) % 7;
						} 
					}
				} else {
					//System.out.println("\t5");
					for (int i = 0; i < startThirteens.length; i++) {
						startThirteens[i] = (startThirteens[i] + 1)%7;
					}
				}
			} else if (j % 4 == 0) {
				//System.out.println("\t2");
				for (int i = 0; i < startThirteens.length; i++){
					if (i < 2){
						startThirteens[i] = (startThirteens[i] + 1) % 7;
					} else {
						startThirteens[i] = (startThirteens[i] + 2) % 7;
					} 
				}
				
				
			//year after leap year
			} else if (j % 100 == 1) {
				if (j % 400 == 1){
					//System.out.println("\t3.1");
					for (int i = 0; i < startThirteens.length; i++){
						if (i >= 2){
							startThirteens[i] = (startThirteens[i] + 1) % 7;
						} else {
							startThirteens[i] = (startThirteens[i] + 2) % 7;
						} 
					}
				} else {
					//System.out.println("\t5");
					for (int i = 0; i < startThirteens.length; i++) {
						startThirteens[i] = (startThirteens[i] + 1)%7;
					}
				}
			} else if (j % 4 == 1) {
				//System.out.println("\t4");
				for (int i = 0; i < startThirteens.length; i++){
					if (i >= 2){
						startThirteens[i] = (startThirteens[i] + 1) % 7;
					} else {
						startThirteens[i] = (startThirteens[i] + 2) % 7;
					} 
				}
			//normal year
			} else {
				//System.out.println("\t5");
				for (int i = 0; i < startThirteens.length; i++) {
					startThirteens[i] = (startThirteens[i] + 1)%7;
				}
			}
			
			
			for (int k = 0; k < 12; k++){
				thirteens[startThirteens[k]]++;
			}
			String output = "";
			for (int i = 0; i < 7; i++){
				output+= thirteens[i] + " " ;
			}
			output = output.trim();
			System.out.println(output);
		}

		
		String output = "";
		for (int i = 0; i < 7; i++){
			output+= thirteens[i] + " " ;
		}
		output = output.trim();
		System.out.println(output);
		out.println(output);
		out.close();
		System.exit(0);
		
	}
}