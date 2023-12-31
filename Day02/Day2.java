import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter("(\\s|, |: |; )");
		int sum = 0;
		for(int i = 0; i<100; i++) {
			s.next();
			int gamenbr = s.nextInt();
			String a = s.next();
			Boolean b = false;
			while (!a.equals("") || b) {
				int nbr = Integer.valueOf(a);
				a = s.next();
				if (a.equals("blue")) {
					if (nbr>14) {
						b = true;
						break;
					}
				}
				if (a.equals("green")) {
					if (nbr > 13) {
						b = true;
						break;
					}
				}
				if (a.equals("red")) {
					if (nbr > 12) {
						b = true;
						break;
					}
				}
				if (s.hasNext()) {
					a = s.next();
				} else {
					break;
				}
			}
			if (b) {
				while(!a.equals("") && s.hasNext()) {
					a = s.next();
				}
			} else {
				sum += gamenbr;
			}
			
		}
		System.out.println(sum);
	}
}
