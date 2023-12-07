import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class Day3Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		int row = 0;
		int test = 0;
		Map<Location,Number> numberMap = new HashMap<Location,Number>();
		Set<Location> gears = new HashSet<Location>();
		while(s.hasNext()) {
			String line = s.next();
			for (int i = 0; i<line.length(); i++) {
				if (Character.isDigit(line.charAt(i))) {
					StringBuilder number = new StringBuilder("");
					while(i<line.length() && Character.isDigit(line.charAt(i))) {
						number.append(line.charAt(i));
						i++;
					}
					Number temp = new Number(Integer.valueOf(number.toString()));
					for (int j = 0; j<number.length(); j++) {
						numberMap.put(new Location(i+j-number.length(),row), temp);
					}
				}
				if (i<line.length() && line.charAt(i) == '*') {
					gears.add(new Location(i,row));
				}
			}
			row++;
		}
		
		for (Location loc : gears) {
			Set<Number> numbers = new HashSet<Number>();
			for (int i = -1; i<2; i++) {
				for (int j = -1; j<2; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					Location temp = new Location(loc.x+i,loc.y+j);
					if (numberMap.containsKey(temp)) {
						numbers.add(numberMap.get(temp));
					}
				}
			}
			if (numbers.size() == 2) {
				int temp = 1;
				for (Number n : numbers) {
					temp *= n.number;
				}
				test++;
				sum += temp;
			}
		}
		System.out.println(test);
		System.out.println(sum);
	}
	
	
	private static class Location {
		int x;
		int y;
		
		private Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Location)) {
				return false;
			}
			if (this.x == ((Location) o).x && this.y == ((Location) o).y) {
				return true;
			}
			return false;
			
		}
		
		@Override
		public int hashCode() {
			return this.x+this.y*150;
		}
		
	}
	
	private static class Number {
		int number;
		
		private Number(int number) {
			this.number = number;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return number;
		}
	}
	
	private static class NumberLocation {
		private int number;
		private int[] location;
		private int nbrOfDigits;
		
		private NumberLocation(int number, int start, int nbrOfDigits) {
			this.number = number;
			this.nbrOfDigits = nbrOfDigits;
			location = new int[nbrOfDigits];
			for (int i = 0; i<nbrOfDigits;i++) {
				location[i] = start+i;
			}
		}
	}

}
