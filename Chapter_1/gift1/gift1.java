/*
ID: cbnewma1
LANG: JAVA
PROG: gift1
*/
import java.util.*;
import java.io.*;
class gift1{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		
		StringTokenizer st = null;
		LinkedHashMap<String, Integer> give= new LinkedHashMap<String, Integer>();
		HashMap<String, Integer> receive = new HashMap<String, Integer>();
		/*accounts.put("Hello", 10);
		accounts.put("Hello", 12);
		for (String key: accounts.keySet()){
			System.out.println(key + ": " + accounts.get(key));
		}
		System.out.println(2/3);
		*/
		int np = Integer.parseInt(f.readLine());
		for (int i = 0; i < np; i++){
			String d = f.readLine();
			give.put(d, 0);
			receive.put(d, 0);
		}
		
		String donor = f.readLine();
		while (donor != null) {
			//String donor = f.readLine();
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int shares = Integer.parseInt(st.nextToken());
			int lossI = 0;
			if (shares != 0) {
				lossI = (start/shares);
				give.put(donor, lossI * shares);
			}
			//System.out.println(donor+": "+(accounts.get(donor) + start - lossT));
			for (int i = 0; i < shares; i++){
				String recip = f.readLine();
				//System.out.println(recip + ": " + (receive.get(recip) + lossI));
				receive.put(recip, receive.get(recip) + lossI); 
			}
			donor = f.readLine();
		}
		
		//output
		for (String name: give.keySet()){
			String ps = name + " " + (receive.get(name)-give.get(name));
			//System.out.println(ps);
			out.println(ps);
		}
		out.close();
		System.exit(0);
	}
}