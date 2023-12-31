import java.io.*;
import java.util.*;

public class Day7Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		List<Dual> list = new ArrayList<Dual>();
		String hand;
		int bid;
		while (s.hasNext()) {
			hand = s.next();
			bid = s.nextInt();
			list.add(new Dual(hand,bid));
		}
		Collections.sort(list);
		for (int i = 0; i<list.size(); i++) {
			sum += list.get(i).bid*(i+1);
		}
		System.out.println(sum);
		

	}
	
	
	private static class Dual implements Comparable<Dual> {
		String hand;
		int bid;
		int strength;
		
		public Dual(String hand, int bid) {
			this.hand = hand;
			this.bid = bid;
			strength = calcStrength(hand);
		}
		
		private int calcStrength(String hand) {
			int[] cards = new int[13];
			for (int i = 0; i<5; i++) {
				cards["23456789TJQKA".indexOf(hand.charAt(i))]++;
			}
			boolean pair = false;
			boolean triple = false;
			for (int i = 0; i<13; i++) {
				if (cards[i] == 5) {
					return 6;
				}
				if (cards[i] == 4) {
					return 5;
				}
				if (cards[i] == 3) {
					triple = true;
					if (pair && triple) {
						return 4;
					}
				}
				if (cards[i] == 2) {
					if (triple) {
						return 4;
					}
					if (pair){
						return 2;
					}
					pair = true;
				}
			}
			if (triple) {
				return 3;
			}
			if (pair) {
				return 1;
			}
			return 0;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Dual)) {
				return false;
			}
			if (this.hand.equals(((Dual) o).hand) && this.bid == ((Dual) o).bid) {
				return true;
			}
			return false;
		}
		
		@Override
		public int compareTo(Dual o) {
			if (this.equals(o)) {
				return 0;
			} else if (this.strength > o.strength) {
				return 1;
			} else if (this.strength < o.strength) {
				return -1;
			}
			for (int i = 0; i<5; i++) {
				if ("23456789TJQKA".indexOf(this.hand.charAt(i)) < "23456789TJQKA".indexOf(o.hand.charAt(i))) {
					return -1;
				} else if ("23456789TJQKA".indexOf(this.hand.charAt(i)) > "23456789TJQKA".indexOf(o.hand.charAt(i))) {
					return 1;
				}
			}
			return 0;
		}
		
	}

}
