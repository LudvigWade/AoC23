import java.io.*;
import java.util.*;

public class Day6Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.next();
		int sum = 1;
		int[] times = new int[4];
		int[] distances = new int[4];
		for (int i = 0; i<4; i++) {
			times[i] = s.nextInt();
		}
		Long time = Long.valueOf(""+times[0]+times[1]+times[2]+times[3]);
		s.next();
		for (int i = 0; i<4; i++) {
			distances[i] = s.nextInt();
		}
		Long distance = Long.valueOf(""+distances[0]+distances[1]+distances[2]+distances[3]);
		int nbrOfWays = 0;
		for (Long i = (long) 0; i<=time; i++) {
			if (i*(time-i) > distance) {
				nbrOfWays++;
			}
		}
		sum *= nbrOfWays;
		System.out.println(sum);
		

	}

}
