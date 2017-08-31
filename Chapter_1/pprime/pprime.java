/*
ID: cbnewma1
LANG: JAVA
TASK: pprime
*/
import java.util.*;
import java.io.*;
class pprime {

	static int[] primes;
	static int primeCount = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken()); //lower bound
		int b = Integer.parseInt(st.nextToken()); //upper bound
		int numDigB = numDig(b);
		int numDigA = numDig(a);
		primes = new int[Math.min(Math.max((b-a)/2, 1), 5761455)]; 
		//5,761,455 is pi(100,000,000) according to https://primes.utm.edu/howmany.html
		
		generatePrimePals(a, b, numDigB, 1, 0, true);
		//generatePrimePals(a, b, numDigB, 1, 0);
		//assuming that primes are sorted
		//if not: 
		Arrays.sort(primes);
		for(int i: primes){
			if (i >= a) {
				out.println(i);
			}
		}
		out.close();
		System.exit(0);
	}
	
	static void generatePrimePals(int lower, int upper, int upperDig, int currDig, int currNum, boolean firstTime) {
		if (firstTime) {
			//runs two for loops, one for odd, one for even
			for(int i = 0; i < 10; i++) {
				//int cand = genDigits(i, currDig);
				if (i >= lower) {
					if (isPrime(i)) {
						primes[primeCount] = i;
						primeCount++;
					}
				}
				generatePrimePals(lower, upper, upperDig, currDig, i, false);
			}
			
			if(11>=lower && 11 <= upper) {
				primes[primeCount] = 11;
				primeCount++;
			}
			
			//don't even have to check even palindromes because they are all divisible by 11
			// for(int i = 0; i < 10; i++) {
// 				if (i*10+i >= lower) {
// 					if (isPrime(i*10+i)) {
// 						primes[primeCount] = i*10+i;
// 						primeCount++;
// 					}
// 				}
// 				generatePrimePals(lower, upper, upperDig, currDig+1, i*10+i, false);
// 			}
		} else {
			//if (currNum == 90209) System.out.println("HERE");
			currNum*=10;
			for(int i = 0; i < 10; i++) {
				int candidate = (int)(i*Math.pow(10, currDig+1)) + currNum + i;
				if (candidate > upper || upperDig < currDig) {
					//if (currNum == 902090) System.out.println("EXIT: " + candidate);
					return;
				}
				//if (currNum == 902090) System.out.println(candidate);
				if (candidate >= lower) {
					//if (currNum == 902090) System.out.println("Checking if prime: " + candidate);
					if (isPrime(candidate)) {
						//if (currNum == 902090) System.out.println("Is prime: " + candidate);
						if (primeCount == primes.length) return;
						primes[primeCount] = candidate;
						primeCount++;
					
					}
				}
				generatePrimePals(lower, upper, upperDig, currDig + 2, candidate, false);
			}
		}
	}
	
	static boolean isPrime(int n) {
		if (n < 5) {
			return false;
		} else if(n%2==0 || n%3==0) {
			return false;
		} else {
			for (int i = 5; i*i <= n; i+=6) {
				if (n%i == 0 || n%(i+2)==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static int genDigits(int digit, int length) {
		int res = 0;
		if (digit == 0) return res;
		while (length > 0) {
			length--;
			res *= 10;
			res += digit;
		}
		return res;
	}
	
	static int numDig(int num) {
		int res = 0;
		while (num != 0) {
			res++;
			num/=10;
		}
		return res;
	}
	
}