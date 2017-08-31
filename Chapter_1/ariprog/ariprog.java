/*
ID: cbnewma1
LANG: JAVA
TASK: ariprog
*/
import java.util.*;
import java.io.*;

public class ariprog {
  public static void main(String[] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    int progLen = Integer.parseInt(f.readLine());
    int upperBoundBS = Integer.parseInt(f.readLine());
//    System.out.println(isBisquare(178, 15));
	int[] bs = genBisquare(upperBoundBS);
	//for(int i: bs) { System.out.print(i+ "\t");}
	//System.out.println(bs.length);
    if (!printProgressions(out, progLen, upperBoundBS, bs)) {
        out.println("NONE");
    }
    out.close();
    System.exit(0);
  }
  
  /* public static boolean printProgressions(PrintWriter out, int n, int m, int[] bisquares) {
//       boolean flag = false;
//       int lastA = -1;
//       int lastB = -1;
//       if (n == 3) {
//       for (int b = 1; b <=  m*m*2; b++) {
//           //for (int a = 0; a <= m*m*2 - b*(n-1); a++) {
//           for (int a = 0; bisquares[a] <= m*m*2 - b*(n-1); a++) {
//               //System.out.println(m*m*2 - n*b);
//               if ( b > (m*m*2 - bisquares[a])/(n-1)) {
//               	break;
//               }
//               
//               if (bisquares[a] == lastA && bisquares[b] == lastB) {
//               	continue;
//               } else {
//               	lastA = bisquares[a];
//               	lastB = bisquares[b];
//               }
//               
//               for (int k = 0; k < n; k++) {
//                   if (!isBisquare(bisquares[a] + b*k, bisquares)) { //Math.min(a + b*k, m)
//                       //if (a == 0 && b == 4) System.out.println(k);
//                       break;
//                   }
//                   if (k == n - 1) {
//                       out.println(bisquares[a] + " " + b);
//                       flag = true;
//                   }
//               }
//           }
//       }
//       } else {
//       for (int b = 4; b <=  m*m*2; b+=4) {
//           //for (int a = 0; a <= m*m*2 - b*(n-1); a++) {
//           for (int a = 0; bisquares[a] <= m*m*2 - b*(n-1); a++) {
//               //System.out.println(m*m*2 - n*b);
//               if ( b > (m*m*2 - bisquares[a])/(n-1)) {
//               	break;
//               }
//               
//               if (bisquares[a] == lastA && bisquares[b] == lastB) {
//               	continue;
//               } else {
//               	lastA = bisquares[a];
//               	lastB = bisquares[b];
//               }
//               
//               for (int k = 0; k < n; k++) {
//                   if (!isBisquare(bisquares[a] + b*k, bisquares)) { //Math.min(a + b*k, m)
//                       //if (a == 0 && b == 4) System.out.println(k);
//                       break;
//                   }
//                   if (k == n - 1) {
//                       out.println(bisquares[a] + " " + b);
//                       flag = true;
//                   }
//               }
//           }
//       }
//       }
//       
//       return flag;
//   }*/

  public static boolean printProgressions(PrintWriter out, int progLen, int m, int[] bisquares) {
  	int currLength = 0;
  	boolean flag = false;
  	int lastBisquare = -1;
  	for (int b = 1; b <= 2*m*m/(progLen-1);) {
  		for (int i = 0; bisquares[i] <= 2 * m * m - b * (progLen-1) ; i++) {
  			
  			if(b > (2*m*m-bisquares[i])/(progLen-1)) {
  				//System.out.println("b: " + b + "\ta: " + bisquares[i] + "\t2m^2: " + 2*m*m + "\tprogLen: " + progLen + "\tSO: " + (2*m*m-bisquares[i])/(progLen));
  				break;
  			}
  			
  			if (bisquares[i] == lastBisquare) continue;
  			else lastBisquare = bisquares[i];
  			
  			for (int j = i + 1; j < bisquares.length; j++) {
  				if (bisquares[j] - bisquares[i] > b*(currLength+1)) {
					currLength = 0;
					break;
  				} else if (bisquares[j] - bisquares[i] == b * (currLength+1)) {
  					currLength++;
  					if (currLength == progLen-1) {
  						out.println(bisquares[i] + " " + b);
  						//System.out.println(bisquares[i] + " " + b);
  						flag = true;
  						currLength = 0;
  						break;
  					}
  				}
  			}
  		}
  		if (b >= 2772) b +=2772;
  		else if (b >= 84) b +=84;
  		else if (b >= 4) b +=4;
  		else b++;
  		
  	}
  	return flag;
  } 
  
  public static int[] genBisquare(int n) {
  	int[] bisquares = new int[(n+1)*(n+2)/2];
  	int arrCount = 0;
  	for (int i = 0; i <= n; i++) {
  		for(int j = i; j <= n; j++, arrCount++) {
  			bisquares[arrCount] = i*i + j*j;
  			//if (bisquares[arrCount] == 1385) System.out.println(i + ", " + j);
  		}
  	} 
  	Arrays.sort(bisquares);
  	//for (int i: bisquares) { System.out.print(i + " ");}
  	return bisquares;
  }
  
  public static boolean isBisquare(int n /*, int limit) { */, int[] bisqrs) {
  
  	for (int x: bisqrs) {
  		if (n == x) {
  			//System.out.println(n);
  			return true;
  		} else if (x > n) {
  			return false;
  		}
  	}
  	return false;
    //OLD CODE: IT WORKS, JUST TAKES TWICE AS LONG... EACH TIME
   //  int bs = 0;
//     for (int i = 0; i <= limit; i++) {
//       for (int j = i; j <= limit; j++) {
//         bs = i*i + j*j;
//         if (bs == n) return true;
//         else if (bs > n) {
//             break;
//         }
//       }
//     }
//     return false;
//   
  }
}

