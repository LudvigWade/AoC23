import java.util.*;
import java.io.*;

public class Day15Task1 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter(",");
		int sum = 0;
		while(s.hasNext()) {
			sum += hash(s.next());
		}
		System.out.println(sum);
	}

	private static int hash(String line) {
		int ret = 0;
		char[] arr = line.toCharArray();
		for (int i = 0; i<arr.length; i++) {
			ret += (int) arr[i];
			ret *= 17;
			ret = ret % 256;
		}
		return ret;
	}
}
