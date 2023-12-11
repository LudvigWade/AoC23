import java.util.*;
import java.io.*;

public class Day9Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		while (s.hasNext()) {
			String[] nbrs = s.nextLine().split("\\s");
			int[] start = new int[nbrs.length];
			for (int i = 0; i<nbrs.length; i++) {
				start[i] = Integer.parseInt(nbrs[i]);
			}
			boolean allZero = false;
			int steps = 0;
			int[] current = start;
			int[] next;
			int[] highest = new int[current.length];
			while(!allZero) {
				next = new int[current.length-1];
				highest[steps] = current[0];
				steps++;
				for (int i = 0; i<next.length; i++) {
					next[i] = current[i+1]-current[i];
				}
				allZero = true;
				for (int i = 0; i<next.length; i++) {
					if (next[i] != 0) {
						allZero = false;
						break;
					}
				}
				current = next;
			}
			for (int i = 0; i<steps; i++) {
				if (i%2 == 1) {
					sum -= highest[i];
				} else {
					sum += highest[i];
				}
			}
		}
		System.out.println(sum);
	}

}
