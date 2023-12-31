import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class Day3Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		int row = 0;
		int[] rowSum = new int[140];
		String row1 = s.next();
		String row2 = s.next();
		String row3 = s.next();
		Set<NumberLocation> setnl = new HashSet<NumberLocation>();
		for (int i = 0; i<row1.length(); i++) {
			if (Character.isDigit(row1.charAt(i))) {
				StringBuilder number = new StringBuilder("");
				while(Character.isDigit(row1.charAt(i))) {
					number.append(row1.charAt(i));
					i++;
				}
				NumberLocation nl = new NumberLocation(Integer.valueOf(number.toString()), i-number.length(), number.length());
				setnl.add(nl);
			}
		}
		for (NumberLocation nl : setnl) {
			if (row1.charAt(nl.location-1) == '.' && row1.charAt(nl.location+nl.nbrOfDigits) == '.') {
				for (int i = -1; i<nl.nbrOfDigits+1; i++) {
					if (row2.charAt(nl.location+i) == '.') {
						continue;
					}
					rowSum[row] += nl.number;
					sum += nl.number;
					break;
				}
				continue;
			}
			rowSum[row] += nl.number;
			sum += nl.number;
		}
		setnl.clear();
		while(s.hasNext()) {
			row++;
			for (int i = 0; i<row2.length(); i++) {
				if (Character.isDigit(row2.charAt(i))) {
					StringBuilder number = new StringBuilder("");
					while(i<row2.length() && Character.isDigit(row2.charAt(i))) {
						number.append(row2.charAt(i));
						i++;
					}
					NumberLocation nl = new NumberLocation(Integer.valueOf(number.toString()), i-number.length(), number.length());
					setnl.add(nl);
				}
			}
			for (NumberLocation nl : setnl) {
				if (nl.location-1<0) {
					if (row2.charAt(nl.location+nl.nbrOfDigits) == '.') {
						for (int i = 0; i<nl.nbrOfDigits+1; i++) {
							if (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.') {
								continue;
							}
							rowSum[row] += nl.number;
							sum += nl.number;
							break;
						}
						continue;
					}
					rowSum[row] += nl.number;
					sum += nl.number;
					continue;
				}
				if (nl.location+nl.nbrOfDigits >= row2.length()) {
					if (row2.charAt(nl.location-1) == '.') {
						for (int i = -1; i<nl.nbrOfDigits; i++) {
							if (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.') {
								continue;
							}
							rowSum[row] += nl.number;
							sum += nl.number;
							break;
						}
						continue;
					}
					rowSum[row] += nl.number;
					sum += nl.number;
					continue;
				}
				if (row2.charAt(nl.location-1) == '.' && row2.charAt(nl.location+nl.nbrOfDigits) == '.') {
					for (int i = -1; i<nl.nbrOfDigits+1; i++) {
						if (nl.location-1 < 0 || nl.location+nl.nbrOfDigits >= row2.length() || (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.')) {
							continue;
						}
						rowSum[row] += nl.number;
						sum += nl.number;
						break;
					}
					continue;
				}
				rowSum[row] += nl.number;
				sum += nl.number;
			}
			row1 = row2;
			row2 = row3;
			row3 = s.next();
			setnl.clear();
		}
		
		
		
		row++;
		for (int i = 0; i<row2.length(); i++) {
			if (Character.isDigit(row2.charAt(i))) {
				StringBuilder number = new StringBuilder("");
				while(i<row2.length() && Character.isDigit(row2.charAt(i))) {
					number.append(row2.charAt(i));
					i++;
				}
				NumberLocation nl = new NumberLocation(Integer.valueOf(number.toString()), i-number.length(), number.length());
				setnl.add(nl);
			}
		}
		for (NumberLocation nl : setnl) {
			if (nl.location-1<0) {
				if (row2.charAt(nl.location+nl.nbrOfDigits) == '.') {
					for (int i = 0; i<nl.nbrOfDigits+1; i++) {
						if (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.') {
							continue;
						}
						rowSum[row] += nl.number;
						sum += nl.number;
						break;
					}
					continue;
				}
				rowSum[row] += nl.number;
				sum += nl.number;
				continue;
			}
			if (nl.location+nl.nbrOfDigits >= row2.length()) {
				if (row2.charAt(nl.location-1) == '.') {
					for (int i = -1; i<nl.nbrOfDigits; i++) {
						if (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.') {
							continue;
						}
						rowSum[row] += nl.number;
						sum += nl.number;
						break;
					}
					continue;
				}
				rowSum[row] += nl.number;
				sum += nl.number;
				continue;
			}
			if (row2.charAt(nl.location-1) == '.' && row2.charAt(nl.location+nl.nbrOfDigits) == '.') {
				for (int i = -1; i<nl.nbrOfDigits+1; i++) {
					if (nl.location-1 < 0 || nl.location+nl.nbrOfDigits >= row2.length() || (row3.charAt(nl.location+i) == '.' && row1.charAt(nl.location+i) == '.')) {
						continue;
					}
					rowSum[row] += nl.number;
					sum += nl.number;
					break;
				}
				continue;
			}
			rowSum[row] += nl.number;
			sum += nl.number;
		}
		setnl.clear();
		
		
		
		row = 139;
		for (int i = 0; i<row3.length(); i++) {
			if (Character.isDigit(row3.charAt(i))) {
				StringBuilder number = new StringBuilder("");
				while(Character.isDigit(row3.charAt(i))) {
					number.append(row3.charAt(i));
					i++;
				}
				NumberLocation nl = new NumberLocation(Integer.valueOf(number.toString()), i-number.length(), number.length());
				setnl.add(nl);
			}
		}
		for (NumberLocation nl : setnl) {
			if (row3.charAt(nl.location-1) == '.' && row3.charAt(nl.location+nl.nbrOfDigits) == '.') {
				for (int i = -1; i<nl.nbrOfDigits+1; i++) {
					if (row2.charAt(nl.location+i) == '.') {
						continue;
					}
					rowSum[row] += nl.number;
					sum += nl.number;
					break;
				}
				continue;
			}
			rowSum[row] += nl.number;
			sum += nl.number;
			setnl.clear();
		}
		System.out.println(sum);
	}
	
	
	
	private static class NumberLocation {
		private int number;
		private int location;
		private int nbrOfDigits;
		
		private NumberLocation(int number, int location, int nbrOfDigits) {
			this.number = number;
			this.location = location;
			this.nbrOfDigits = nbrOfDigits;
		}
	}

}
