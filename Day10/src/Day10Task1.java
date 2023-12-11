import java.util.*;
import java.io.*;

public class Day10Task1 {
	char[][] data;
	ArrayList<Character> t1;
	ArrayList<Character> t2;
	ArrayList<Character> t3;
	ArrayList<Character> t4;
	
	public Day10Task1(char[][] data) {
		this.data = data;
		t1 = new ArrayList<Character>();
		t2 = new ArrayList<Character>();
		t3 = new ArrayList<Character>();
		t4 = new ArrayList<Character>();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		String a = s.nextLine();
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
		Day10Task1 d = new Day10Task1(data);
		final int[] start = {i,k};
		Thread t1 = new Thread(() -> {
			int[] previous = {start[0], start[1]};
			int[] current = {start[0]-1, start[1]};
			int[] next = d.nextStep(previous,current);
			while (next != null) {
				if (next[0] == -1 && next[1] == -1) {
					d.t1.add(data[start[0]][start[1]]);
					break;
				}
				d.t1.add(data[next[0]][next[1]]);
				previous = current;
				current = next;
				next = d.nextStep(previous, current);
			}
			System.out.println("t1 = " + d.t1.size());
		});
		Thread t2 = new Thread(() -> { 
			int[] previous = {start[0], start[1]};
			int[] current = {start[0], start[1]+1};
			int[] next = d.nextStep(previous,current);
			while (next != null) {
				if (next[0] == -1 && next[1] == -1) {
					d.t2.add(data[start[0]][start[1]]);
					break;
				}
				d.t2.add(data[next[0]][next[1]]);
				previous = current;
				current = next;
				next = d.nextStep(previous, current);
			}
			System.out.println("t2 = " + d.t2.size());
		});
		Thread t3 = new Thread(() -> { 
			int[] previous = {start[0], start[1]};
			int[] current = {start[0]+1, start[1]};
			int[] next = d.nextStep(previous,current);
			while (next != null) {
				if (next[0] == -1 && next[1] == -1) {
					d.t3.add(data[start[0]][start[1]]);
					break;
				}
				d.t3.add(data[next[0]][next[1]]);
				previous = current;
				current = next;
				next = d.nextStep(previous, current);
			}
			System.out.println("t3 = " + d.t3.size());
		});
		Thread t4 = new Thread(() -> { 
			int[] previous = {start[0], start[1]};
			int[] current = {start[0], start[1]-1};
			int[] next = d.nextStep(previous,current);
			while (next != null) {
				if (next[0] == -1 && next[1] == -1) {
					d.t4.add(data[start[0]][start[1]]);
					break;
				}
				d.t4.add(data[next[0]][next[1]]);
				previous = current;
				current = next;
				next = d.nextStep(previous, current);
			}
			System.out.println("t4 = " + d.t4.size());
			
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	private int[] nextStep(int[] prev, int[] current) {
		switch(data[current[0]][current[1]]) {
		case '|':
			if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0]-1, current[1]};
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0]+1, current[1]};
				return temp;
			} else {
				return null;
			}
		case '-':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0], current[1]-1};
				return temp;
			} else if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0], current[1]+1};
				return temp;
			} else {
				return null;
			}
		case 'L':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0]-1, current[1]};
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]+1};
				return temp;
			} else {
				return null;
			}
		case 'J':
			if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0]-1, current[1]};
				return temp;
			} else if (prev[0] - current[0] == -1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]-1};
				return temp;
			} else {
				return null;
			}
		case '7':
			if (prev[1] - current[1] == -1 && current[0] == prev[0]) {
				int[] temp = {current[0]+1, current[1]};
				return temp;
			} else if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]-1};
				return temp;
			} else {
				return null;
			}
		case 'F':
			if (prev[1] - current[1] == 1 && current[0] == prev[0]) {
				int[] temp = {current[0]+1, current[1]};
				return temp;
			} else if (prev[0] - current[0] == 1 && current[1] == prev[1]) {
				int[] temp = {current[0], current[1]+1};
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

}
