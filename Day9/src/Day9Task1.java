import java.util.*;
import java.io.*;

public class Day9Task1 {

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
			int[] current = start;
			int[] next;
			while(!allZero) {
				next = new int[current.length-1];
				sum += current[current.length-1];
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
		}
		System.out.println(sum);
	}

}
