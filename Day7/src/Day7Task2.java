import java.io.*;
import java.util.*;

public class Day7Task2 {

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
				cards["J23456789TQKA".indexOf(hand.charAt(i))]++;
			}
			boolean pair = false;
			boolean triple = false;
			int usedCards = -1;
			int jokersUsed = 0;
			for (int i = 1; i<13; i++) {
				if (cards[i]+cards[0] == 5) {
					return 6;
				}
			}
			for (int i = 1; i<13; i++) {
				if (cards[i]+cards[0] == 4) {
					return 5;
				}
			}
			for (int i = 1; i<13; i++) {
				if (cards[i]+cards[0]-jokersUsed == 3) {
					jokersUsed = cards[0];
					usedCards = i;
					triple = true;
				}
			}
			for (int i = 1; i<13; i++) {
				if (i == usedCards) {
					continue;
				}
				if (cards[i]+cards[0]-jokersUsed == 2) {
					jokersUsed += cards[0]-jokersUsed;
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
				if ("J23456789TQKA".indexOf(this.hand.charAt(i)) < "J23456789TQKA".indexOf(o.hand.charAt(i))) {
					return -1;
				} else if ("J23456789TQKA".indexOf(this.hand.charAt(i)) > "J23456789TQKA".indexOf(o.hand.charAt(i))) {
					return 1;
				}
			}
			return 0;
		}
		
	}

}
