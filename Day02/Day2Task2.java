import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2Task2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter("(\\s|, |: |; )");
		int sum = 0;
		int everyRow = 0;
		for(int i = 0; i<100; i++) {
			s.next();
			int gamenbr = s.nextInt();
			everyRow += gamenbr;
			String a = s.next();
			Boolean b = false;
			int[] minimum = {0,0,0};
			while (!a.equals("") || b) {
				int nbr = Integer.valueOf(a);
				a = s.next();
				if (a.equals("blue")) {
					if (nbr>minimum[0]) {
						minimum[0] = nbr;
					}
				}
				if (a.equals("green")) {
					if (nbr>minimum[1]) {
						minimum[1] = nbr;
					}
				}
				if (a.equals("red")) {
					if (nbr>minimum[2]) {
						minimum[2] = nbr;
					}
				}
				if (s.hasNext()) {
					a = s.next();
				} else {
					break;
				}
			}
			sum += minimum[0]*minimum[1]*minimum[2];
		}
		System.out.println(sum);
		System.out.println(everyRow);
	}
}
