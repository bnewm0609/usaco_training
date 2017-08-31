/*
ID: cbnewma1
LANG: JAVA
TASK: wormhole
*/
import java.util.*;
import java.io.*;
public class wormhole {
	
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		
		int numHoles = Integer.parseInt(f.readLine());
		Hole[] wormholes = new Hole[numHoles];
		//int maxX = 0;
		StringTokenizer st;
		for (int i = 0; i < numHoles; i++) {
			st = new StringTokenizer(f.readLine());
			wormholes[i] = new Hole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		setUpRightHoles(wormholes);
		//Hole printing and setting up right holes is working
		//for (Hole w: wormholes) {
		//	System.out.println(w);
		//}
		
		//Testing `hasLoop`
		//wormholes[0].setAttachedHole(wormholes[1]);
		//wormholes[2].setAttachedHole(wormholes[3]);
		//System.out.println("Includes a loop? " + hasLoop(wormholes));
		
		count = 0;
		//System.out.println(wormholes[0]);
		numLoops(wormholes, numHoles);
		//System.out.println(count);
		out.println(count);
		
		out.close();
		System.exit(0);
		
		
	}
	
	public static void setUpRightHoles(Hole[] holes) { //runs max 132 times
		for (Hole w1: holes) {
			for (Hole w2: holes) {
				if (w1.getY() == w2.getY() && w1.getX() < w2.getX()) {
					if (w1.getRightHole() == null) {
						w1.setRightHole(w2);
					} else {
						if (w2.getX() - w1.getX() < w1.getRightHole().getX() - w1.getX()) {
							w1.setRightHole(w2);
						}
					}
				}
			}
		}
	}
	
	public static void numLoops (Hole[] holes, int numUnconnected) {
		Hole con1 = null;
		Hole con2 = null;
		if (numUnconnected == 2) {
			for (int i = 0; i < holes.length; i++) {
				if (holes[i].getAttachedHole() == null) {
					if (con1 == null) {
						con1 = holes[i];
					} else {
						con1.setAttachedHole(holes[i]);
						con2 = holes[i];
					}
				}
			}
			if (hasLoop(holes)) {
				System.out.println("+1");
				count++;
			}
			//for (Hole h: holes) {
			//	System.out.println(h);
			//}
			con1.resetAttachedHole();
			con2.resetAttachedHole();
		} else {
			int i;
			for (i = 0; i < holes.length; i++) {
				if (holes[i].getAttachedHole() == null) {
					con1 = holes[i];
					break;
				}
			}
			for (int j = i+1; j < holes.length; j++) {
				//System.out.println("*** in the loop ***");
				if (holes[j].getAttachedHole() == null) {
					con1.setAttachedHole(holes[j]);
					con2 = holes[j];
					numLoops(holes, numUnconnected - 2);
					con1.resetAttachedHole();
					con2.resetAttachedHole();
				}
			}
			
		}
	}
	
	public static boolean hasLoop (Hole[] holes) {
		//loop through all possible combinations
		int counter = 0;
		for(int i = 0; i < holes.length; i ++) {
			Hole tracker = holes[i];
			Hole starter = holes[i];
			
			while (tracker != null) {
				tracker = tracker.getAttachedHole().getRightHole();
				if (tracker == starter) { //they will both point to the same Hole, so this actually works
					return true;
				}
			}
		}
		return false;
	}
}

class Hole {
	private Hole attachedHole;
	private Hole rightHole;
	private int x;
	private int y;
	
	public Hole(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setAttachedHole(Hole other) {
		attachedHole = other;
		if (other.getAttachedHole() != this) {
			other.setAttachedHole(this);
		}
	}
	
	public void setRightHole(Hole other) {
		rightHole = other;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Hole getAttachedHole() {
		return attachedHole;	
	}
		
	public Hole getRightHole() {
		return rightHole;	
	}
	
	public void resetAttachedHole() {
		attachedHole = null;
	}
	
	//for debugging
	// public String toString() {
// 		if (rightHole == null && attachedHole != null) {
// 			return "(" + x + ", " + y + ")\t(Right: (" + rightHole +  ")\t(Attached: (" + attachedHole.getX() + ", " + attachedHole.getY() + ")";
// 		} else if (rightHole != null && attachedHole == null) {
// 			return "(" + x + ", " + y + ")\t(Right: (" + rightHole.getX() + ", " + rightHole.getY() +  ")\t(Attached: (" + attachedHole + ")";		
// 		} else if (rightHole == null && attachedHole == null) {
// 			return "(" + x + ", " + y + ")\t(Right: (" + rightHole +  ")\t(Attached: (" + attachedHole + ")";
// 		} else {
// 		 return "(" + x + ", " + y + ")\t(Right: (" + rightHole.getX() + ", " + rightHole.getY()+  ")\t(Attached: (" + attachedHole.getX() + ", " + attachedHole.getY() + ")";
// 		}
// 	}
}