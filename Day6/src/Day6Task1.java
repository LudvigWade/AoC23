import java.io.*;
import java.util.*;

public class Day6Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.next();
		int sum = 1;
		int[] times = new int[4];
		int[] distance = new int[4];
		for (int i = 0; i<4; i++) {
			times[i] = s.nextInt();
		}
		s.next();
		for (int i = 0; i<4; i++) {
			distance[i] = s.nextInt();
		}
		for (int j = 0; j<times.length; j++) {
			int nbrOfWays = 0;
			for (int i = 0; i<=times[j]; i++) {
				if (i*(times[j]-i) > distance[j]) {
					nbrOfWays++;
				}
			}
			sum *= nbrOfWays;
		}
		System.out.println(sum);
	}

}
