import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		Set<Integer> winningNbrs = new HashSet<Integer>();
		while (s.hasNext()) {
			Scanner q = new Scanner(s.nextLine());
			q.useDelimiter("(\\s)");
			winningNbrs.clear();
			String line = q.next();
			if (line.equals("Card")) {
				while (q.next() == "") {
				}
				while (q.hasNext()) {
					line = q.next();
					if (line.equals("|")) {
						break;
					}
					if (line.equals("")) {
						continue;
					}
					try {
						int nbr = Integer.parseInt(line);
						winningNbrs.add(nbr);
					} catch (NumberFormatException e) {
						continue;
					}
				}
				int score = 1;
				while (q.hasNext()) {
					try {
						int nbr = Integer.parseInt(q.next());
						if (winningNbrs.contains(nbr)) {
							score *= 2;
						}
					} catch (NumberFormatException e) {
						continue;
					}
				}
				if (score > 1) {
					sum += score/2;
				}
			}
		}
		System.out.println(sum);

	}

}
