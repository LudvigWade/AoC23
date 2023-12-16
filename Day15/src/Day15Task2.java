import java.util.*;
import java.io.*;

public class Day15Task2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter(",");
		int sum = 0;
		ArrayList<Aocstring>[] hashMap = new ArrayList[256];
		for (int i = 0; i<256; i++) {
			hashMap[i] = new ArrayList<Aocstring>();
		}
		while(s.hasNext()) {
			String line = s.next();
			Aocstring temp;
			if (line.charAt(line.length()-1) == '-') {
				temp = new Aocstring(line.substring(0,line.length()-1),0);
				hashMap[temp.hashCode].remove(temp);
			} else {
				temp = new Aocstring(line.substring(0,line.length()-2),Character.getNumericValue(line.charAt(line.length()-1)));
				if (hashMap[temp.hashCode].contains(temp)) {
					for (int j = 0; j<hashMap[temp.hashCode].size(); j++) {
						if (hashMap[temp.hashCode].get(j).equals(temp)) {
							hashMap[temp.hashCode].get(j).changeLens(temp.lens);
						}
					}
				} else {
					hashMap[temp.hashCode].add(temp);
				}
			}
		}
		for (int i = 0; i<256; i++) {
			for (int j = 0; j<hashMap[i].size(); j++) {
				sum += (i+1)*(j+1)*hashMap[i].get(j).lens;
			}
		}
		System.out.println(sum);
	}
	
	
	
	private static class Aocstring {
		String line;
		int hashCode;
		int lens;
		
		public Aocstring(String line, int lens) {
			this.line = line;
			this.lens = lens;
			int ret = 0;
			char[] arr = this.line.toCharArray();
			for (int i = 0; i<arr.length; i++) {
				ret += (int) arr[i];
				ret *= 17;
				ret = ret % 256;
			}
			hashCode = ret;
		}
		
		public void changeLens(int newLens) {
			lens = newLens;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Aocstring)) {
				return false;
			}
			
			if (this.line.equals(((Aocstring) o).line)) {
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return hashCode;
		}
	}
}

