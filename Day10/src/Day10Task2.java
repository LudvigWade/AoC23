import java.util.*;
import java.io.*;

public class Day10Task2 {
	char[][] data;
	int[][] from;
	int[][] to;
	boolean[][] counted;
	String GREEN = "\u001B[32m";
	String RESET = "\u001B[0m";
	
	public Day10Task2(char[][] data) {
		this.data = data;
		from = new int[data.length][data[1].length];
		to = new int[data.length][data[1].length];
		counted = new boolean[data.length][data[1].length];
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		String a = s.nextLine();
		int sumEnclosed = 0;
		char[][] data = new char[140][a.length()];
		data[0] = a.toCharArray();
		for (int i = 1; i<140; i++) {
			data[i] = s.nextLine().toCharArray();
		}
		int i,j,k=-1;
		for (i = 0; i<data.length; i++) {
			for (j = 0; j<data[1].length; j++) {
				if (data[i][j] == 'S') {
					k = j;
					break;
				}
			}
			if (k != -1) {
				break;
			}
		}
		Day10Task2 d = new Day10Task2(data);
		final int[] start = {i,k};
		int[] previous = {start[0], start[1]};
		int[] current = {start[0]-1, start[1]};
		int[] next = d.nextStep(previous,current);
		while (next != null) {
			if (next[0] == -1 && next[1] == -1) {
				break;
			}
			previous = current;
			current = next;
			next = d.nextStep(previous, current);
		}
		boolean inside = false;
		boolean isWall = false;
		int firstWall = 0;
		int lastWall = 0;
		int prevSumEnclosed = 0;
		for (i = 0; i<d.from.length; i++) {
			for (j = 0; j<d.from[i].length; j++) {
				if (d.from[i][j]>0 && !isWall) {
					firstWall = d.from[i][j];
					lastWall = d.to[i][j];
					isWall = true;
				} else if (d.from[i][j] == 0 && isWall) {
					if (!((firstWall == 1 && (d.from[i][j-1]+2)%4 == 1) || (firstWall == 3 && (d.from[i][j-1]+2)%4 == 3) || 
							(lastWall == 1 &&  (d.to[i][j-1]+2)%4 == 1) || (lastWall == 3 &&  (d.to[i][j-1]+2)%4 == 3) || 
							firstWall == d.to[i][j-1] || lastWall == d.from[i][j-1])) {
						inside = !inside;
					}
					isWall = false;
				}
				
				
				if (!isWall && inside) {
					sumEnclosed++;
					d.counted[i][j] = true;
				}
			}
//			System.out.println("Line " + i + ": " + (sumEnclosed - prevSumEnclosed));
//			System.out.print((sumEnclosed - prevSumEnclosed)+ " ");
			prevSumEnclosed = sumEnclosed;
			inside = false;
			isWall = false;
			firstWall = 0;
			lastWall = 0;
		}
//		d.print();
		System.out.println(sumEnclosed);
		
	}
	
	private int[] nextStep(int[] prev, int[] current) { // 1 = from above, 2 = from right, 3 = from below, 4 = from left
		switch(data[current[0]][current[1]]) {
		case '|':
			if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0]-1, current[1]};
				this.from[current[0]][current[1]] = 3;
				this.to[current[0]][current[1]] = 1;
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0]+1, current[1]};
				this.from[current[0]][current[1]] = 1;
				this.to[current[0]][current[1]] = 3;
				return temp;
			} else {
				return null;
			}
		case '-':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0], current[1]-1};
				this.from[current[0]][current[1]] = 2;
				this.to[current[0]][current[1]] = 4;
				return temp;
			} else if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0], current[1]+1};
				this.from[current[0]][current[1]] = 4;
				this.to[current[0]][current[1]] = 2;
				return temp;
			} else {
				return null;
			}
		case 'L':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0]-1, current[1]};
				this.from[current[0]][current[1]] = 2;
				this.to[current[0]][current[1]] = 1;
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]+1};
				this.from[current[0]][current[1]] = 1;
				this.to[current[0]][current[1]] = 2;
				return temp;
			} else {
				return null;
			}
		case 'J':
			if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0]-1, current[1]};
				this.from[current[0]][current[1]] = 4;
				this.to[current[0]][current[1]] = 1;
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]-1};
				this.from[current[0]][current[1]] = 1;
				this.to[current[0]][current[1]] = 4;
				return temp;
			} else {
				return null;
			}
		case '7':
			if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0]+1, current[1]};
				this.from[current[0]][current[1]] = 4;
				this.to[current[0]][current[1]] = 3;
				return temp;
			} else if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]-1};
				this.from[current[0]][current[1]] = 3;
				this.to[current[0]][current[1]] = 4;
				return temp;
			} else {
				return null;
			}
		case 'F':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0]+1, current[1]};
				this.from[current[0]][current[1]] = 2;
				this.to[current[0]][current[1]] = 3;
				return temp;
			} else if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]+1};
				this.from[current[0]][current[1]] = 3;
				this.to[current[0]][current[1]] = 2;
				return temp;
			} else {
				return null;
			}
		case 'S':
			int[] temp = {-1,-1};
			return temp;
		default:
			return null;
		}
	}
	
	private void print() {
		for (int i = 0; i<140; i++) {
			for (int j = 0; j<140; j++) {
				if (j == 139) {
					if (this.to[i][j] == 0) {
						System.out.println(" 0 ");
					} else if (this.to[i][j] == 1) {
						System.out.println(" ↑ ");
					} else if (this.to[i][j] == 2) {
						System.out.println(" → ");
					} else if (this.to[i][j] == 3) {
						System.out.println(" ↓ ");
					} else if (this.to[i][j] == 4) {
						System.out.println(" ← ");
					}
				} else {
					if (this.counted[i][j]) {
						if (this.to[i][j] == 0) {
							System.out.print("[0]");
						}
					} else {
						if (this.to[i][j] == 0) {
							System.out.print(" 0 ");
						} else if (this.to[i][j] == 1) {
							System.out.print(" ↑ ");
						} else if (this.to[i][j] == 2) {
							System.out.print(" → ");
						} else if (this.to[i][j] == 3) {
							System.out.print(" ↓ ");
						} else if (this.to[i][j] == 4) {
							System.out.print(" ← ");
						}
					}
				}
			}
		}
	}

}

