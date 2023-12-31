import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		int row = -1;
		int[] nbrOfCards = new int[218];
		for (int i = 0; i<nbrOfCards.length; i++) {
			nbrOfCards[i]++;
		}
		Set<Integer> winningNbrs = new HashSet<Integer>();
		while (s.hasNext()) {
			Scanner q = new Scanner(s.nextLine());
			row++;
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
				int nbrOfWins = 0;
				while (q.hasNext()) {
					try {
						int nbr = Integer.parseInt(q.next());
						if (winningNbrs.contains(nbr)) {
							nbrOfWins++;
						}
					} catch (NumberFormatException e) {
						continue;
					}
				}
				for (int i = 0; i<nbrOfWins; i++) {
					nbrOfCards[row+i+1] += nbrOfCards[row];
				}
			}
		}
		for (int i = 0; i<nbrOfCards.length; i++) {
			sum += nbrOfCards[i];
		}
		System.out.println(sum);
	}

}
